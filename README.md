# project-cloud
Nhóm 2 đề tài sử dụng docker để ảo hóa server Ubuntu
Thành viên
Võ Phúc Sơn - 17110360
Nguyễn Toàn Khoa - 17110317
Võ Thành Đạt - 17110280

Nhóm em xây dựng website java trên IDE eclipse
Cần 3 ec2 để chạy website
trên các ec2 thực hiện 2 lệnh
docker pull sonvo123/os:ubuntu
docker pull sonvo123/os:centos
Bước 1: tạo 1 container sql và import database đính kèm trong github usercloud trên ec2
Bước 2: chạy container sql
Bước 3: trông phần src/main/java 
phần vn.cloud.config thay đổi các thông số chúng em đã ghi chú ở file Config.java
(Nếu deploy trên ec2 thì thay đổi bằng ip private và tải file key lên ec2 và thay đổi path của key)
Bước 5: Tạo tài khoản hoặc kiểm tra database để lấy tài khoản (role =1 là quyền quản trị)
