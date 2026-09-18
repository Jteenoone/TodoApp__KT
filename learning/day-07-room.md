# Ngày 7 — Cơ sở dữ liệu Room

## Mục tiêu

Lưu task/project thật trên máy, không mất dữ liệu khi tắt app.

## Lý thuyết ngắn

Room gồm Entity (bảng), DAO (hàm truy vấn), Database (cấu hình) và Repository (lớp gọi DAO cho ViewModel). Model UI và Entity có thể tách nhau.

## Bài thực hành

1. Thêm dependency Room.
2. Tạo `TaskEntity` và `TaskDao` với `observeTasks(): Flow<List<TaskEntity>>`.
3. Tạo `TodoDatabase`.
4. Cho Repository đọc/ghi task qua DAO.
5. Kiểm tra thêm task, tắt app, mở lại task vẫn còn.

## Tiêu chí hoàn thành

- ViewModel không gọi Room trực tiếp.
- Hiểu sự khác nhau giữa model và entity.

## Thử thách thêm

Lưu cả `Project` và `Category`, sau đó đọc task theo `projectId`.
