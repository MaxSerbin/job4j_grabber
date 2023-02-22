package ru.job4j.ood.tdd.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class TemplateGenTest {

    @Test
    public void whenProduceIsOk() {
        TemplateGen templateGen = new TemplateGen();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> keys = new HashMap<>();
        keys.put("name", "Max");
        keys.put("subject", "you");
        String rsl = templateGen.produce(template, keys);
        String exp = "I am a Max, Who are you? ";
        assertThat(rsl).isEqualTo(exp);
    }

    @Test
    public void whenWithoutTemplateAndKeys() {
        TemplateGen templateGen = new TemplateGen();
        String template = "";
        Map<String, String> keys = new HashMap<>();
        assertThatThrownBy(() -> templateGen.produce(template, keys))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenWithoutKeys() {
        TemplateGen templateGen = new TemplateGen();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> keys = new HashMap<>();
        assertThatThrownBy(() -> templateGen.produce(template, keys))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenWithoutTemplate() {
        TemplateGen templateGen = new TemplateGen();
        String template = "";
        Map<String, String> keys = new HashMap<>();
        keys.put("name", "Max");
        keys.put("subject", "you");
        assertThatThrownBy(() -> templateGen.produce(template, keys))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenWithoutOneKey() {
        TemplateGen templateGen = new TemplateGen();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> keys = new HashMap<>();
        keys.put("name", "Max");
        assertThatThrownBy(() -> templateGen.produce(template, keys))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenExcessKey() {
        TemplateGen templateGen = new TemplateGen();
        String template = "Who are ${subject}? ";
        Map<String, String> keys = new HashMap<>();
        keys.put("name", "Max");
        keys.put("subject", "you");
        assertThatThrownBy(() -> templateGen.produce(template, keys))
                .isInstanceOf(IllegalArgumentException.class);
    }
}