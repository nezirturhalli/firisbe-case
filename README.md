# firisbe-case

Tasarım Genel Bakışı:

Sunulan gereksinimlere dayanarak SecurePay projesi için bir tasarım oluşturdum. Proje, Java ve Spring Boot çerçevesi kullanılarak geliştirildi, ilişkisel  veritabanı kullanıldı. Müşteri ve ödeme yönetimi için Restful uç noktaları sunuldu. Hata önleme, doğrulama kontrolleri, günlükleme ve Swagger ile API belgeleri uygulandı.

Kullandığım Teknolojiler:

Java 17

Spring Boot 3 (MVC, Data JPA)

H2 Veritabanı 

Swagger (API belgeleri için)

JUnit ve Mockito (test için)

SLF4J ve Logback (loglama için)

1- Depoyu klonlayın: git clone https://github.com/nezirturhalli/firisbe-case

2- Proje dizinine gidin: cd firisbe-case

3- Uygulamayı Maven kullanarak derleyin ve çalıştırın: mvn spring-boot:run

4- Swagger UI belgelerine erişin:

Web tarayıcınızı açın ve http://localhost:8080/swagger-ui/index.html adresine gidin.

5- Kontroller:

Müşteri Kontrolcüsü:

POST /customers - Yeni bir müşteri kaydedin.

GET /customers - Tüm müşterileri getir.

Ödeme Kontrolcüsü:

POST /payments - Bir müşteri için yeni bir ödeme kaydedin.

GET /payments/by-customer/{customerId} - Müşteri id'sine göre ödemeleri getir .

GET /payments/by-card-number/{cardNumber} - Müşteri kart numarasına göre ödemeleri getir .

GET /payments/by-date-range - Belirli bir tarih aralığındaki tüm ödemeleri listeleyin.

6-Doğrulamalar:

Müşteriler mevcut bir e-posta ile kaydedilemez.

Bir kredi kartı numarası yalnızca bir müşteri için kaydedilebilir.

Ödeme tutarları 0'dan büyük olmalıdır.

7- Günlükleme:

İstisnalar, SLF4J ve Logback kullanılarak kaydedilecektir.

8- API Belgeleri:

Swagger UI, API uç noktaları için etkileşimli belgeler sağlayacaktır.

