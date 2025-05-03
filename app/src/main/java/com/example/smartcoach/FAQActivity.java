package com.example.smartcoach;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcoach.models.FAQItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class FAQActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        RecyclerView recyclerView = findViewById(R.id.faqRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<FAQItem> faqList = new ArrayList<>();
        faqList.add(new FAQItem("Як змінити пароль?", "Перейдіть у розділ 'Налаштування' → 'Зміна пароля'."));
        faqList.add(new FAQItem("Як відновити акаунт?", "Натисніть 'Забули пароль?' на екрані входу."));
        faqList.add(new FAQItem("Як додати тренування до календаря?", "У розділі 'Генератор тренувань' оберіть програму та натисніть 'Додати до календаря'."));
        faqList.add(new FAQItem("Чому не відображається прогрес тренувань?", "Перевірте підключення до інтернету та оновіть сторінку."));
        faqList.add(new FAQItem("Як синхронізувати дані з Google Fit?", "У 'Налаштуваннях' оберіть 'Підключення додатків' → 'Google Fit'."));
        faqList.add(new FAQItem("Як змінити ціль тренування?", "У розділі 'Профіль' → 'Мої цілі' оберіть нову ціль."));
        faqList.add(new FAQItem("Чому не працює відтворення відео тренувань?", "Перевірте наявність оновлень додатку в Google Play."));
        faqList.add(new FAQItem("Як видалити акаунт?", "Напишіть у підтримку з вашого email, пов’язаного з акаунтом."));
        faqList.add(new FAQItem("Як змінити email?", "У 'Налаштуваннях' → 'Мій email' введіть нову адресу."));
        faqList.add(new FAQItem("Чому не приходить лист для відновлення пароля?", "Перевірте папку 'Спам' або введіть email ще раз."));
        faqList.add(new FAQItem("Як додати власне тренування?", "У 'Генераторі тренувань' натисніть '+' → 'Створити власне'."));
        faqList.add(new FAQItem("Чому не зберігаються дані про вагу?", "Перезапустіть додаток та перевірте доступ до пам’яті."));
        faqList.add(new FAQItem("Як відновити преміум-доступ?", "Напишіть у підтримку з темою 'Відновлення преміуму'."));
        faqList.add(new FAQItem("Як відключити сповіщення?", "У 'Налаштуваннях' → 'Сповіщення' зніміть позначки."));
        faqList.add(new FAQItem("Чому не працює відстеження пульсу?", "Перевірте підключення смарт-годинника або додатку для здоров’я."));
        faqList.add(new FAQItem("Як переглянути історію тренувань?", "У розділі 'Календар' оберіть потрібну дату."));
        faqList.add(new FAQItem("Як змінити мову інтерфейсу?", "У 'Налаштуваннях' → 'Мова' оберіть потрібну."));
        faqList.add(new FAQItem("Чому не відображається фото профілю?", "Надайте додатку доступ до галереї."));
        faqList.add(new FAQItem("Як експортувати дані у PDF?", "У розділі 'Звіти' натисніть 'Експорт' → 'PDF'."));
        faqList.add(new FAQItem("Як додати друга у додаток?", "У розділі 'Спільнота' натисніть '+' та введіть email друга."));
        faqList.add(new FAQItem("Чому не працює відтворення аудіо-тренувань?", "Перевірте налаштування звуку на пристрої."));
        faqList.add(new FAQItem("Як змінити час тренування?", "У календарі натисніть на тренування та оберіть 'Редагувати'."));
        faqList.add(new FAQItem("Чому не синхронізуються дані з Apple Health?", "Дозвольте доступ у налаштуваннях iOS."));
        faqList.add(new FAQItem("Як переглянути статистику за місяць?", "У розділі 'Звіти' оберіть період '30 днів'."));
        faqList.add(new FAQItem("Як зв’язатися з підтримкою?", "У розділі 'Налаштування' → 'Підтримка' напишіть повідомлення."));

        recyclerView.setAdapter(new FAQAdapter(faqList));

        // Кнопка "Назад"
        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());

        // Настройка Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_settings);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(FAQActivity.this, HomeActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_generate_workout) {
                startActivity(new Intent(FAQActivity.this, GenerateWorkoutActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_settings) {
                // Уже находимся на этой странице
                return true;
            }
            return false;
        });
    }
}