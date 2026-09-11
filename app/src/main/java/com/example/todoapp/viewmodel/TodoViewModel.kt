package com.example.todoapp.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Work
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.todoapp.model.Category
import com.example.todoapp.model.Project
import com.example.todoapp.model.Task
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class TodoViewModel: ViewModel() {
    
    // Helper function để tạo thời gian nhanh cho ngày hôm nay
    private fun todayAt(hour: Int, minute: Int): LocalDateTime {
        return LocalDateTime.now().with(LocalTime.of(hour, minute))
    }

    // Helper function để tạo thời gian cho các ngày bất kỳ (offset so với hôm nay)
    private fun dateAt(daysOffset: Long, hour: Int, minute: Int): LocalDateTime {
        return LocalDateTime.now().plusDays(daysOffset).with(LocalTime.of(hour, minute))
    }

    var tasks by mutableStateOf<List<Task>>(listOf(
        // --- 7 NGÀY TRƯỚC ---
        Task(101, "Ăn tối cùng gia đình", "Nhà hàng ngon", 1.0f, 4, dateAt(-7, 19, 0), dateAt(-7, 21, 0)),
        Task(102, "Học Unit Test", "JUnit 5 basics", 1.0f, 2, dateAt(-7, 21, 30), dateAt(-7, 22, 30)),
        Task(103, "Gửi báo cáo tuần", "Cho sếp tổng", 1.0f, 1, dateAt(-6, 9, 0), dateAt(-6, 10, 0)),
        Task(104, "Chạy bộ 5km", "Công viên", 1.0f, 3, dateAt(-6, 17, 30), dateAt(-6, 18, 30)),
        Task(105, "Mua sắm đồ gia dụng", "Siêu thị Go", 1.0f, 4, dateAt(-5, 10, 0), dateAt(-5, 12, 0)),
        Task(106, "Đọc sách", "The Alchemist", 1.0f, 2, dateAt(-5, 22, 0), dateAt(-5, 23, 0)),
        Task(107, "Fix bug UI Login", "Màn hình login", 1.0f, 1, dateAt(-4, 14, 0), dateAt(-4, 16, 0)),
        Task(108, "Tập Yoga sáng", "Bài tập nhẹ", 1.0f, 3, dateAt(-4, 7, 0), dateAt(-4, 7, 45)),
        Task(109, "Họp dự án X", "Bàn về timeline", 1.0f, 1, dateAt(-3, 10, 0), dateAt(-3, 11, 30)),
        Task(110, "Café cùng bạn cũ", "Quán quen", 1.0f, 4, dateAt(-3, 15, 0), dateAt(-3, 17, 0)),
        Task(111, "Học Compose Animation", "Deep dive", 1.0f, 2, dateAt(-2, 20, 0), dateAt(-2, 22, 0)),
        Task(112, "Giặt đồ & Dọn dẹp", "Việc nhà", 1.0f, 4, dateAt(-2, 8, 0), dateAt(-2, 9, 0)),
        Task(113, "Nghiên cứu API mới", "REST vs GraphQL", 1.0f, 1, dateAt(-1, 14, 0), dateAt(-1, 15, 30)),
        Task(114, "Thiền buổi tối", "15 phút thư giãn", 1.0f, 3, dateAt(-1, 23, 0), dateAt(-1, 23, 15)),

        // --- NGÀY HÔM NAY ---
        Task(1, "Tập Yoga sáng", "Khởi động ngày mới với 30p Yoga", 1.0f, 3, todayAt(7, 0), todayAt(7, 30)),
        Task(2, "Ăn sáng & Café", "Thưởng thức bữa sáng và lên kế hoạch", 1.0f, 4, todayAt(7, 30), todayAt(8, 15)),
        Task(3, "Kiểm tra Email", "Xử lý các thông báo quan trọng", 0.8f, 1, todayAt(8, 30), todayAt(9, 0)),
        Task(4, "Họp Stand-up", "Báo cáo tiến độ với Team", 1.0f, 1, todayAt(9, 0), todayAt(9, 30)),
        Task(5, "Code Login Flow", "Hoàn thiện tính năng đăng nhập", 0.6f, 1, todayAt(9, 30), todayAt(11, 30)),
        Task(6, "Review PR #56", "Xem lại code của đồng nghiệp", 0.0f, 1, todayAt(11, 30), todayAt(12, 0)),
        Task(7, "Nghỉ trưa", "Ăn trưa và trò chuyện cùng Team", 0.0f, 4, todayAt(12, 0), todayAt(13, 0)),
        Task(8, "Chợp mắt 30p", "Nghỉ ngơi lấy lại năng lượng", 0.0f, 4, todayAt(13, 0), todayAt(13, 45)),
        Task(9, "Fix bug giao diện", "Sửa lỗi hiển thị trên màn hình nhỏ", 0.2f, 1, todayAt(14, 0), todayAt(15, 30)),
        Task(10, "Học Kotlin Flow", "Xem video hướng dẫn StateFlow", 0.0f, 2, todayAt(15, 30), todayAt(16, 30)),
        Task(11, "Café chiều", "Giải lao ngắn", 0.0f, 4, todayAt(16, 30), todayAt(17, 0)),
        Task(12, "Sync thiết kế", "Họp với UI/UX Designer", 0.0f, 1, todayAt(17, 0), todayAt(18, 0)),
        Task(13, "Viết Document", "Cập nhật README.md", 0.0f, 1, todayAt(18, 0), todayAt(18, 30)),
        Task(14, "Tập Gym", "Cardio và tập tạ nhẹ", 0.0f, 3, todayAt(19, 0), todayAt(20, 0)),
        Task(15, "Mua thực phẩm", "Mua thực phẩm cho ngày mai", 0.0f, 4, todayAt(20, 0), todayAt(20, 45)),
        Task(16, "Ăn tối", "Bữa tối cùng gia đình", 0.0f, 4, todayAt(21, 0), todayAt(22, 0)),
        Task(17, "Đọc sách Clean Code", "Chương 5: Formatting", 0.0f, 2, todayAt(22, 0), todayAt(22, 45)),
        Task(18, "Lên kế hoạch mai", "Ghi chú các việc quan trọng", 0.0f, 4, todayAt(22, 45), todayAt(23, 0)),
        Task(19, "Thiền", "Thư giãn trước khi ngủ", 0.0f, 3, todayAt(23, 0), todayAt(23, 15)),
        Task(20, "Viết Nhật ký", "Tổng kết ngày hôm nay", 0.0f, 4, todayAt(23, 15), todayAt(23, 30)),

        // --- 7 NGÀY TIẾP THEO ---
        Task(201, "Khởi động Sprint mới", "Planning cùng team", 0.0f, 1, dateAt(1, 9, 0), dateAt(1, 10, 30)),
        Task(202, "Đi cắt tóc", "Salon quen thuộc", 0.0f, 4, dateAt(1, 18, 0), dateAt(1, 19, 0)),
        Task(203, "Kiểm tra sức khỏe", "Khám định kỳ", 0.0f, 3, dateAt(2, 8, 0), dateAt(2, 11, 0)),
        Task(204, "Học tiếng Anh", "Luyện nghe nói", 0.0f, 2, dateAt(2, 20, 0), dateAt(2, 21, 0)),
        Task(205, "Review Module mới", "Phần Dashboard", 0.0f, 1, dateAt(3, 14, 0), dateAt(3, 15, 0)),
        Task(206, "Thanh toán hóa đơn", "Tiền điện thoại", 0.0f, 4, dateAt(3, 22, 0), dateAt(3, 22, 15)),
        Task(207, "Bóng đá cùng team", "Sân vận động", 0.0f, 3, dateAt(4, 18, 0), dateAt(4, 20, 0)),
        Task(208, "Ghi chú ý tưởng App", "Mô hình kinh doanh", 0.0f, 2, dateAt(4, 22, 30), dateAt(4, 23, 0)),
        Task(209, "Seminar công nghệ", "Chủ đề AI & Mobile", 0.0f, 1, dateAt(5, 9, 0), dateAt(5, 11, 0)),
        Task(210, "Tiệc sinh nhật Nam", "Nhà hàng lẩu", 0.0f, 4, dateAt(5, 19, 0), dateAt(5, 22, 0)),
        Task(211, "Bảo trì máy tính", "Dọn dẹp file rác", 0.0f, 4, dateAt(6, 10, 0), dateAt(6, 11, 0)),
        Task(212, "Tập leo núi trong nhà", "Rèn luyện thể lực", 0.0f, 3, dateAt(6, 16, 0), dateAt(6, 18, 0)),
        Task(213, "Chuẩn bị bản Demo", "Gửi cho khách hàng", 0.0f, 1, dateAt(7, 14, 0), dateAt(7, 17, 0)),
        Task(214, "Lên lịch tuần kế tiếp", "Buổi tối cuối tuần", 0.0f, 4, dateAt(7, 21, 0), dateAt(7, 22, 0))
    ))
    private set

    var projects by mutableStateOf<List<Project>>(listOf(
        Project(1, "Dự án App Mobile", 1, "Phát triển Todo App", LocalDate.now().minusDays(5), LocalDate.now().plusDays(25)),
        Project(2, "Khóa học Kotlin", 3, "Học Jetpack Compose nâng cao", LocalDate.now().minusDays(10), LocalDate.now().plusDays(20)),
        Project(3, "Sức khỏe & Fitness", 4, "Luyện tập mỗi ngày", LocalDate.now(), LocalDate.now().plusDays(90)),
        Project(4, "Việc Cá Nhân", 2, "Các hoạt động sinh hoạt", LocalDate.now().minusDays(30), LocalDate.now().plusDays(30))
    ))
    private set

    var categories by mutableStateOf<List<Category>>(listOf(
        Category(1, "Công việc", Color(0xFFF478B8), Color(0xFFFFE4F2), Icons.Default.Work),
        Category(2, "Cá nhân", Color(0xFF5F33E1), Color(0xFFEDE7FF), Icons.Default.Person),
        Category(3, "Học tập", Color(0xFF4CAF50), Color(0xFFE4F7E7), Icons.Default.School),
        Category(4, "Sức khỏe", Color(0xFFFF9800), Color(0xFFFFF1DD), Icons.Default.FitnessCenter)
    ))
    private set

    fun getTaskByDate(date: LocalDate): List<Task> {
        val startOfDay = date.atStartOfDay()
        val endOfDay = date.atTime(LocalTime.MAX)
        return tasks.filter { it.startDate.isBefore(endOfDay) && it.endDate.isAfter(startOfDay) }
            .sortedBy { it.startDate }
    }

    fun getProjectById(projectId: Int): Project? = projects.find { it.id == projectId }
    fun getCategoryById(categoryId: Int): Category? = categories.find { it.id == categoryId }
    fun getTaskById(taskId: Int): Task? = tasks.find { it.id == taskId }

    fun getTasksByProject(projectId: Int): List<Task> = tasks.filter { it.projectId == projectId }

    fun updateTask(updatedTask: Task) {
        tasks = tasks.map { if (it.id == updatedTask.id) updatedTask else it }
    }

    fun addTask(task: Task) {
        tasks = tasks + task
    }

    fun deleteTask(taskId: Int) {
        tasks = tasks.filter { it.id != taskId }
    }

    fun addProject(project: Project) {
        projects = projects + project
    }
}
