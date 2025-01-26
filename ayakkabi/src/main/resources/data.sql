-- Admin tablosunu oluştur
CREATE TABLE IF NOT EXISTS adminler (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- İlk admin kullanıcısını ekle (şifre: admin123)
INSERT INTO adminler (username, password, role)
VALUES ('admin', '$2a$10$5Q/UQNN0EwHH7OzFpZw8/.YzAIqZ9Qd5YGxXE.QYo0G8vqt3EeIIi', 'ADMIN')
ON CONFLICT (username) DO NOTHING;

-- Ürünleri ekle
INSERT INTO urunler (ad, marka, model, kategori, fiyat, stok, resim) 
VALUES 
('Ürün 1', 'X', 'Y', 'kadin', 999, 10, 'kadin1.1.jpg'),
('Ürün 2', 'A', 'B', 'kadin', 999, 10, 'kadin2.1.jpg'),
('Ürün 3', 'C', 'D', 'kadin', 999, 10, 'kadin3.1.jpg'),
('Ürün 4', 'E', 'F', 'erkek', 1299, 10, 'erkek1.1.jpg'),
('Ürün 5', 'G', 'H', 'erkek', 999, 10, 'erkek2.1.jpg'),
('Ürün 6', 'I', 'J', 'erkek', 999, 10, 'erkek3.1.jpg'),
('Ürün 7', 'K', 'L', 'cocuk', 999, 10, 'cocuk1.1.jpg'),
('Ürün 8', 'M', 'N', 'cocuk', 999, 10, 'cocuk2.1.jpg'),
('Ürün 9', 'O', 'P', 'cocuk', 999, 10, 'cocuk3.1.jpg'); 