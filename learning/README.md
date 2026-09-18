# Khóa học cấp tốc Android Kotlin TodoApp

Đây là giáo án học trực tiếp trên app TodoApp này. Mỗi ngày có 3 phần:

- Lý thuyết ngắn: hiểu đúng khái niệm trước khi code.
- Đọc code hiện tại: biết nó đang nằm ở đâu trong app.
- Bài thực hành: sửa hoặc thêm tính năng thật trong TodoApp.

## App hiện tại đang có gì

- Kotlin + Jetpack Compose.
- Nhiều màn hình: Start, Home, Calendar, Add Project, Project Detail, Add Task.
- Navigation Compose.
- `TodoViewModel` đang giữ state bằng `mutableStateOf`.
- Dữ liệu task/project/category đang là mock data trong bộ nhớ.
- UI đã tách thành các composable nhỏ trong `ui/compose`.

## Bạn đang thiếu gì để lên trình Android Kotlin

- Kotlin cần chắc hơn: data class, enum, sealed class, null safety, extension function.
- Compose state: `remember`, `rememberSaveable`, state hoisting, recomposition.
- Kiến trúc app: UI -> ViewModel -> Repository -> Data source.
- StateFlow/Flow thay vì chỉ dùng `mutableStateOf` trong ViewModel.
- Lưu trữ cục bộ bằng Room.
- Form validation và giao diện báo lỗi.
- Kiểm thử logic bằng unit test.
- Navigation có tham số, back stack, route rõ ràng.
- Đưa text/màu vào resource để app dễ bảo trì.
- Clean code: đặt tên, tách file, tránh lặp logic trong UI.

## Lộ trình nhanh

Nếu học mỗi ngày 2–3 giờ, bạn có thể đi theo 10 ngày:

1. `day-01-task-status.md`: Task status, enum, tách logic hiển thị.
2. `day-02-compose-state.md`: State trong Compose và form Add Task.
3. `day-03-viewmodel-events.md`: Đưa hành động thêm/sửa/xóa vào ViewModel đúng cách.
4. `day-04-navigation.md`: Navigation, route, truyền tham số.
5. `day-05-architecture.md`: Tách Repository và data source.
6. `day-06-flow-stateflow.md`: StateFlow và UI state.
7. `day-07-room.md`: Lưu task/project bằng Room.
8. `day-08-validation-error.md`: Validate form và hiển thị lỗi thân thiện.
9. `day-09-testing.md`: Unit test cho progress, filter, add/delete.
10. `day-10-polish-capstone.md`: Tổng hợp thành bản app tốt hơn.

## Cách học

Mỗi ngày dùng một branch riêng hoặc commit nhỏ. Sau mỗi bài:

- Chạy app.
- Tự giải thích lại bằng lời của bạn.
- Viết 3 dòng note: hôm nay học gì, lỗi gì gặp, cách sửa.

Dùng app này làm sản phẩm tập là cách học rất ổn: bạn không chỉ đọc lý thuyết, bạn biến nó thành tính năng thật.
