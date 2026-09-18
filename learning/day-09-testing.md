# Ngày 9 — Unit test cho logic Todo

## Mục tiêu

Biết test logic mà không cần mở app.

## Lý thuyết ngắn

Unit test phù hợp cho logic thuần: tính progress project, lọc task theo ngày, validate form, tạo id mới và chuyển progress sang status. Test tốt nên nhỏ, rõ và có tên thể hiện hành vi.

## Bài thực hành

1. Test `Task.status`: progress 0 -> TODO, 0.5 -> IN_PROGRESS, 1 -> COMPLETED.
2. Test `calculateProjectProgress`.
3. Test validation Add Task.
4. Chạy `testDebugUnitTest` và đọc report khi fail.

```kotlin
@Test
fun progressZero_returnsTodoStatus() {
    // arrange
    // act
    // assert
}
```

## Tiêu chí hoàn thành

- Có ít nhất 5 unit test có ý nghĩa.
- Test pass.
- Biết đọc report test khi thất bại.

## Thử thách thêm

Tạo fake repository và test event `createTask` của ViewModel.
