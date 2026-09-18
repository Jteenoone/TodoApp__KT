# Ngày 4 — Navigation Compose và route có tham số

## Mục tiêu

Hiểu cách di chuyển giữa các màn hình và truyền `projectId`.

## Lý thuyết ngắn

Navigation Compose gồm `NavHost`, `composable(route)` và `navController.navigate(...)`. Route có tham số giúp màn hình biết cần hiển thị dữ liệu nào:

```kotlin
"project_detail/{projectId}"
navController.navigate("project_detail/$projectId")
```

## Bài thực hành

1. Đổi tên `AppNaviagtion.kt` thành `AppNavigation.kt` nếu muốn.
2. Tạo helper route `projectDetail(projectId: Int)` và `addTask(projectId: Int)`.
3. Thay chuỗi navigate trực tiếp bằng helper route.
4. Nếu project không tồn tại, hiện `Project not found` và nút Back.

## Tiêu chí hoàn thành

- Không lặp chuỗi route ở nhiều nơi.
- Giải thích được `navArgument("projectId")` dùng để làm gì.

## Thử thách thêm

Thêm màn hình Task Detail với route `task_detail/{taskId}`.
