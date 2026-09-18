# Ngày 2 — State trong Compose và form Add Task

## Mục tiêu

Hiểu cách Compose ghi nhớ và cập nhật dữ liệu trên màn hình. Áp dụng vào `AddTaskScreen`.

## Lý thuyết ngắn

Trong Compose, UI là kết quả của state. Khi state đổi, Compose gọi lại composable cần thiết để vẽ lại UI.

- `remember`: giữ giá trị qua các lần recomposition nhưng có thể mất khi process bị kill.
- `rememberSaveable`: giống `remember`, đồng thời lưu được kiểu đơn giản khi xoay màn hình.
- State hoisting: composable con nhận `value` và `onChange`, composable cha giữ state. Cách này giúp UI dễ tái sử dụng và dễ test.

Trong app, `AddTaskContent` đang dùng `rememberSaveable` cho tên và tiêu đề, còn ngày giờ dùng `remember`.

## Đọc code hiện tại

- `ui/screen/AddTaskScreen.kt`
- `ui/compose/TaskNameInputCard.kt`
- `ui/compose/TaskDescriptionInputCard.kt`
- `ui/compose/DateInputCard.kt`
- `ui/compose/TimeInputCard.kt`

## Bài thực hành

1. Thêm biến `isNameError` trong `AddTaskContent`.
2. Khi bấm `Create Task` mà tên rỗng, không tạo task và hiện lỗi.
3. Khi người dùng nhập lại tên, xóa lỗi.
4. Nút `Create Task` chỉ enable khi tên không rỗng và `endDate` sau `startDate`.

```kotlin
val canCreate = name.isNotBlank() && endDate.isAfter(startDate)
```

## Tiêu chí hoàn thành

- Tên rỗng thì không thêm task.
- Nhập tên hợp lệ thì nút hoạt động lại.
- Giải thích được vì sao `name` dùng `rememberSaveable`.

## Thử thách thêm

Tách state của form thành `AddTaskFormState` gồm `name`, `title`, `startDate`, `endDate`.
