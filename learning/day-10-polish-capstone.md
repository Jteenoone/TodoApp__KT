# Ngày 10 — Capstone: biến TodoApp thành bản demo tốt

## Mục tiêu

Tổng hợp những gì đã học thành một phiên bản app sạch hơn và dùng được hơn.

## Checklist capstone

- Task có status rõ ràng bằng enum.
- Add Task có validation.
- ViewModel không tự ôm hết mock data.
- UI đọc state từ ViewModel rõ ràng.
- Navigation route không lặp chuỗi lung tung.
- Có ít nhất 5 unit test.
- Nếu đã học Room: task vẫn lưu được sau khi tắt app.

## Bài thực hành tổng hợp

Làm tính năng “Complete Task”:

1. Trong Project Detail, thêm nút/check icon để hoàn thành task.
2. Khi bấm, đổi progress thành `1f`.
3. Status đổi thành `COMPLETED`.
4. Project progress cập nhật theo.
5. Thêm test cho hàm cập nhật progress.

## Lý thuyết cần nắm

```text
User click -> UI event -> ViewModel -> update state/data -> Compose recomposes
```

Nếu hiểu được flow này, bạn đã nắm được xương sống của Android Compose.

## Bài nộp cuối khóa

Viết file `learning/final-retrospective.md` gồm:

- 5 điều bạn học được.
- 3 lỗi từng gặp và cách sửa.
- 2 phần muốn học sau.
- 1 tính năng muốn tự làm tiếp.

## Hướng học tiếp

Coroutine nâng cao, Room và migration, Dependency Injection với Hilt, Retrofit/API, Compose performance và UI testing.
