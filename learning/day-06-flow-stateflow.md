# Ngày 6 — Flow, StateFlow và UI state

## Mục tiêu

Chuyển từ `mutableStateOf<List<Task>>` sang `StateFlow` theo cách làm Android hiện đại.

## Lý thuyết ngắn

`StateFlow` là dòng dữ liệu luôn có giá trị hiện tại. UI Compose có thể thu thập bằng:

```kotlin
val uiState by viewModel.uiState.collectAsState()
```

Gom state màn hình vào một data class giúp trạng thái rõ ràng hơn:

```kotlin
data class TodoUiState(
    val tasks: List<Task> = emptyList(),
    val projects: List<Project> = emptyList(),
    val categories: List<Category> = emptyList()
)
```

## Bài thực hành

1. Tạo `TodoUiState`.
2. Tạo `_uiState = MutableStateFlow(TodoUiState())` và expose `StateFlow` read-only.
3. Khi thêm/xóa task, cập nhật bằng `_uiState.update { state -> ... }`.
4. UI đọc `uiState.tasks` thay vì đọc trực tiếp `viewModel.tasks`.

## Tiêu chí hoàn thành

- State màn hình có một model rõ ràng.
- UI collect state từ ViewModel.
- Giải thích được vì sao `MutableStateFlow` nên để private.

## Thử thách thêm

Thêm `selectedDate: LocalDate` và event đổi ngày trong Calendar.
