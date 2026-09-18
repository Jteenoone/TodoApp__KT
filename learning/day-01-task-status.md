# Ngày 1 — Mô hình hóa trạng thái Task

## Mục tiêu

Tách **trạng thái nghiệp vụ** của công việc ra khỏi **tiến độ hiển thị** để mã dễ đọc, ít lỗi và dễ mở rộng.

## Lý thuyết ngắn

`Float` phù hợp với một đại lượng liên tục, ví dụ 0%, 50% hoặc 80% hoàn thành. Nó không diễn đạt tốt các trạng thái nghiệp vụ. Ví dụ `0f`, `0.5f`, `1f` không tự nói cho người đọc biết ý nghĩa của chúng; khi cần thêm `CANCELLED` hoặc `PAUSED` thì cách suy luận trạng thái từ `progress` không còn đủ.

`enum class` chỉ cho phép một tập giá trị hữu hạn. Compiler sẽ báo lỗi ở những `when` chưa xử lý đầy đủ khi thêm trạng thái mới. Đó là lợi ích chính của kiểu dữ liệu an toàn.

## Quan sát trong app hiện tại

- `model/Task.kt`: `status` đang được suy ra từ `progress`.
- Các UI card nhận `progress` để vẽ thanh tiến độ: giữ nguyên mục đích đó.
- Các UI hiển thị màu/tên trạng thái đang so sánh chuỗi như `"In Progress"`: đây là mục tiêu refactor tiếp theo.

## Bài thực hành (45–60 phút)

1. Tạo `model/TaskStatus.kt`:

```kotlin
enum class TaskStatus {
    TODO,
    IN_PROGRESS,
    COMPLETED
}
```

2. Trong `Task`, thay property `val status = when { ... }` bằng một property có kiểu `TaskStatus`. Ở bài đầu, bạn có thể vẫn suy ra từ `progress`, nhưng kết quả phải là enum, không phải `String`.

```kotlin
val status: TaskStatus
    get() = when {
        progress <= 0f -> TaskStatus.TODO
        progress < 1f -> TaskStatus.IN_PROGRESS
        else -> TaskStatus.COMPLETED
    }
```

3. Trong `ui/compose/ProjectTaskItemCard.kt` và `TaskDetailCard.kt`, đổi các `when (task.status)` từ so sánh String sang `TaskStatus.TODO`, `TaskStatus.IN_PROGRESS`, `TaskStatus.COMPLETED`.

4. Build app và kiểm tra mỗi trạng thái vẫn có đúng màu/nhãn.

## Tiêu chí hoàn thành

- Không còn chuỗi `"To Do"`, `"In Progress"`, `"Completed"` được dùng để quyết định logic UI.
- Project build thành công.
- Task có `progress` 0, 0.2 và 1 lần lượt hiển thị TODO, IN_PROGRESS, COMPLETED.

## Thử thách thêm (10 phút)

Tạo extension để biến `TaskStatus` thành nhãn hiển thị:

```kotlin
fun TaskStatus.label(): String = when (this) {
    TaskStatus.TODO -> "To do"
    TaskStatus.IN_PROGRESS -> "In progress"
    TaskStatus.COMPLETED -> "Completed"
}
```

Sau này nhãn này sẽ được chuyển vào `strings.xml` để hỗ trợ đa ngôn ngữ.
