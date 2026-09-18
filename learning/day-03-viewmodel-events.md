# Ngày 3 — ViewModel và event trong app Todo

## Mục tiêu

Hiểu vai trò của ViewModel: giữ state cho màn hình và xử lý hành động người dùng.

## Lý thuyết ngắn

UI nên tập trung hiển thị. ViewModel nên giữ dữ liệu và xử lý logic nghiệp vụ. Thay vì UI tự tạo `id = viewModel.tasks.size + 1`, hãy để ViewModel tạo task mới. Nếu task bị xóa, `size + 1` có thể trùng id; sau này Room cũng sẽ tự sinh id.

## Đọc code hiện tại

- `viewmodel/TodoViewModel.kt`
- `ui/screen/AddTaskScreen.kt`
- `ui/screen/ProjectDetailScreen.kt`

## Bài thực hành

1. Tạo hàm `createTask(...)` trong `TodoViewModel`.
2. Tính id bằng `(tasks.maxOfOrNull { it.id } ?: 0) + 1`.
3. Trong `AddTaskScreen`, gọi `viewModel.createTask(...)` thay vì tự tạo `Task`.
4. Kiểm tra thêm task sau khi xóa task vẫn không trùng id.

## Tiêu chí hoàn thành

- UI không còn tự tạo id.
- Logic tạo task nằm trong ViewModel.
- Giải thích được sự khác nhau giữa state và event.

## Thử thách thêm

Tạo `updateTaskProgress(taskId: Int, progress: Float)` và cập nhật task bằng `copy`.
