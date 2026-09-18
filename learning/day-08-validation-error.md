# Ngày 8 — Validation, error state và trải nghiệm người dùng

## Mục tiêu

Làm form Add Task/Add Project khó nhập sai hơn.

## Lý thuyết ngắn

Validation nên trả về kết quả rõ ràng, không chỉ là boolean:

```kotlin
sealed interface ValidationResult {
    data object Valid : ValidationResult
    data class Invalid(val message: String) : ValidationResult
}
```

UI hiển thị message, còn ViewModel hoặc helper function quyết định hợp lệ hay không.

## Bài thực hành

1. Validate name không rỗng.
2. Kiểm tra end time phải sau start time.
3. Kiểm tra title không quá 120 ký tự.
4. Hiện lỗi ngay dưới input và disable nút tạo khi form không hợp lệ.
5. Tạo helper `TaskValidation.kt`.

## Tiêu chí hoàn thành

- Lỗi hiển thị rõ và đúng cho từng trường.
- Logic validation không nằm cứng trong Button.
- Giải thích được vì sao nên tách validation khỏi UI.

## Thử thách thêm

Viết unit test cho validation.
