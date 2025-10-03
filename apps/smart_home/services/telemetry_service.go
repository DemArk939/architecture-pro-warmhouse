package services

import (
	"bytes"
	"encoding/json"
	"errors"
	"fmt"
	"io"
	"log"
	"net/http"
	"smarthome/models"
	"time"
)

// TelemetryService
type TelemetryService struct {
	BaseURL    string
	HTTPClient *http.Client
}

// Telemetry represents the response from the temperature API
type Telemetry struct {
	Id         int               `json:"id"`
	DeviceId   int               `json:"deviceId"`
	Status     string            `json:"status"`
	Parameters map[string]string `json:"parameters"`
}

// NewTelemetryService creates a new temperature service
func NewTelemetryService(baseURL string) *TelemetryService {
	return &TelemetryService{
		BaseURL: baseURL,
		HTTPClient: &http.Client{
			Timeout: 10 * time.Second,
		},
	}
}

func (s *TelemetryService) AddTelemetry(sensor models.Sensor) (*http.Response, error) {
	// Формируем объект Telemetry на основе Sensor
	telemetry := Telemetry{
		DeviceId: sensor.ID,
		Status:   sensor.Status,
		Parameters: map[string]string{ // Карта параметров
			"temperature": fmt.Sprintf("%.2f°C", sensor.Value),
			"location":    sensor.Location,
		},
	}
	// Маршалируем объект Telemetry в JSON
	payloadBytes, err := json.Marshal(telemetry)
	if err != nil {
		return nil, errors.New("ошибка при конвертации в JSON")
	}

	log.Printf("Добавление телеметрии: %+v", telemetry)

	// Формируем URL для POST-запроса
	url := fmt.Sprintf("%s/telemetry", s.BaseURL)

	// Готовим тело запроса
	reqBody := bytes.NewReader(payloadBytes)

	// Отправляем POST-запрос
	request, err := http.NewRequest(http.MethodPost, url, reqBody)
	if err != nil {
		return nil, fmt.Errorf("ошибка при подготовке запроса: %w", err)
	}

	// Устанавливаем заголовок Content-Type
	request.Header.Set("Content-Type", "application/json")

	// Отправляем запрос
	response, err := s.HTTPClient.Do(request)
	if err != nil {
		return nil, fmt.Errorf("ошибка при отправке запроса: %w", err)
	}

	// Проверяем статус-код ответа
	if response.StatusCode != http.StatusOK {
		body, _ := io.ReadAll(response.Body)
		return nil, fmt.Errorf("неожиданный статус-код (%d): %s", response.StatusCode, body)
	}

	return response, nil
}
