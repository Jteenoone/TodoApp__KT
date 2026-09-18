# Ngày 5 — Kiến trúc UI, ViewModel, Repository

## Mục tiêu

Tách dữ liệu ra khỏi ViewModel để app dễ mở rộng.

## Lý thuyết ngắn

```text
UI -> ViewModel -> Repository -> Data source
```

- UI: hiển thị và gửi event.
- ViewModel: giữ state màn hình, gọi repository.
- Repository: che giấu nguồn dữ liệu.
- Data source: mock data, Room hoặc API.

Hiện tại `TodoViewModel` đang làm quá nhiều việc: chứa mock data, giữ state, lọc task, thêm và xóa task.

## Bài thực hành

1. Tạo package `data` và file `TodoRepository.kt`.
2. Chuyển mock data ban đầu sang repository.
3. Cho ViewModel gọi repository để lấy danh sách và thao tác thêm/xóa/sửa.
4. Tạo `FakeTodoRepository` để dùng cho preview hoặc test.

## Tiêu chí hoàn thành

- ViewModel ngắn hơn.
- Mock data không nằm hết trong ViewModel.
- Giải thích được vì sao cần Repository trước khi học Room.
