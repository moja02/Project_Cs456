# 🚗 SpotLy - Smart Parking Management System

![Laravel](https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white)
![PHP](https://img.shields.io/badge/PHP-777BB4?style=for-the-badge&logo=php&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-323330?style=for-the-badge&logo=javascript&logoColor=F7DF1E)

**SpotLy** هو نظام برمجي ذكي ومتكامل لإدارة مواقف السيارات، يهدف إلى القضاء على العشوائية وتسهيل عملية حجز المواقف باستخدام التكنولوجيا الحديثة. تم تطوير هذا النظام كمشروع تخرج في هندسة البرمجيات، ويوفر بيئة تفاعلية تربط بين السائقين، الموظفين الميدانيين، ونظام إدارة المواقف.

---

## ✨ المميزات الرئيسية (Key Features)

* 🎟️ **نظام الحجز التفاعلي الذكي:**
    * **الحجز المبدئي:** يمنح السائق مهلة 20 دقيقة للوصول إلى الساحة.
    * **الحجز الفعلي:** يبدأ فور وصول السائق للساحة وتأكيد الموظف الميداني.
* 💳 **المحفظة الإلكترونية (Digital Wallet):** نظام دفع دقيق يعتمد على النقاط (خصم 2.5 نقطة لكل ساعة)، مع سياسات استرجاع مرنة عند الإلغاء.
* ⚖️ **نظام المخالفات والعقوبات التلقائي (Penalty System):** * إلغاء تلقائي للحجوزات المبدئية المنتهية وتسجيل مخالفة (Fake Booking).
    * حظر تلقائي للحساب عند تجاوز 3 مخالفات لضمان الاستخدام العادل للموارد.
* ✉️ **نظام الإشعارات المتقدم (Notifications & Emails):**
    * سجل تنبيهات حي داخل لوحة تحكم المستخدم.
    * إرسال رسائل بريد إلكتروني رسمية فورية (تأكيد الحجز، المخالفات، الحظر الإداري).
* 🔐 **أمان عالي ومصادقة (Security & Authentication):**
    * تسجيل الدخول الآمن.
    * استعادة كلمة المرور عبر نظام (OTP - One Time Password) بخطوات مشفرة وصلاحية زمنية محددة.

---

## 🛠️ التقنيات المستخدمة (Tech Stack)

* **الواجهة الخلفية (Backend):** Laravel Framework (PHP)
* **قاعدة البيانات (Database):** MySQL
* **الواجهة الأمامية (Frontend):** HTML5, CSS3, JavaScript, Bootstrap
* **التحكم في الإصدار (Version Control):** Git & GitHub

---

## ⚙️ متطلبات التشغيل (Prerequisites)

تأكد من تنصيب البرامج التالية على جهازك قبل البدء:
* [PHP](https://www.php.net/) (>= 8.1)
* [Composer](https://getcomposer.org/)
* [MySQL](https://www.mysql.com/) (أو XAMPP/Laragon)
* [Git](https://git-scm.com/)

---

## 🚀 طريقة التثبيت والتشغيل (Installation)

 اتبع الخطوات التالية لتشغيل المشروع على بيئتك المحلية:

1. **استنساخ المستودع (Clone the repository):**
   ```bash
   git clone [ضع_رابط_مستودع_جيت_هاب_هنا]
   cd spotly
