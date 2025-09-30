package services

import (
	"bytes"
	"encoding/json"
	"errors"
	"fmt"
	"io"
	"net/http"
	"smarthome/models"
	"strings"
	"time"
)

// DeviceService
type DeviceService struct {
	BaseURL    string
	HTTPClient *http.Client
}

// Device
type Device struct {
	Id           int    `json:"id"`
	DeviceType   string `json:"deviceType"`
	HouseId      int    `json:"houseId"`
	LocationId   int    `json:"locationId"`
	UserId       int    `json:"userId"`
	SerialNumber string `json:"serialNumber"`
	Status       string `json:"status"`
	IsActive     bool   `json:"isActive"`
	Url          string `json:"url"`
}

// NewDeviceService creates a new service
func NewDeviceService(baseURL string) *DeviceService {
	return &DeviceService{
		BaseURL: baseURL,
		HTTPClient: &http.Client{
			Timeout: 10 * time.Second,
		},
	}
}

func (s *DeviceService) AddDevice(sensor models.Sensor) (*http.Response, error) {
	// Формируем объект Device на основе Sensor
	device := Device{
		Id:           sensor.ID,
		DeviceType:   strings.ToLower(string(sensor.Type)),
		HouseId:      0, // дополнительное вычисление
		LocationId:   0, // дополнительное вычисление
		UserId:       0, // дополнительное вычисление
		SerialNumber: "123132",
		Status:       sensor.Status,
		IsActive:     true,
		Url:          "", // дополнительное вычисление
	}

	// Маршалируем объект Device в JSON
	payloadBytes, err := json.Marshal(device)
	if err != nil {
		return nil, errors.New("ошибка при конвертации устройства в JSON")
	}

	// Формируем URL для POST-запроса
	url := fmt.Sprintf("%s/device", s.BaseURL)

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
