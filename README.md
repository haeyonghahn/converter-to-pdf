## 🏠 프로젝트 개요
이미지 파일을 pdf 파일로 변환해주는 라이브러리입니다.

---

### 🏭 설치 방법
#### Maven
```xml
<dependency>
    <groupId>io.github.haeyonghahn</groupId>
    <artifactId>converter-to-pdf</artifactId>
    <version>0.0.1</version>
</dependency>
```

#### Gradle
```kotlin
implementation 'io.github.haeyonghahn:converter-to-pdf:0.0.1'
```

---

### 💻 사용 방법
```java
public static void main(String[] args) {
	File exampleFile = new File("...");
	
	PdfSource pdfSource = new ImageSource(exampleFile);
	PdfConverter pdfConverter = new PdfConverter(pdfSource)
            .addModule(new ImageConvertModule())
            .addModule(new PasswordBasedEncryptModule("qwer1234", "qwer1234"));
	
	Pdf pdf = pdfConverter.convert(pdfSource);
}
```

---

### 📖 라이센스
This project is licensed under the [MIT License](https://github.com/haeyonghahn/converter-to-pdf/blob/master/LICENSE).   
Feel free to use, modify, and distribute it as per the license terms.

© 2024 [haeyong Hahn]. All rights reserved.

