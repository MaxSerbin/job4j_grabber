package ru.job4j.ood.isp;

/**
 * Нарушение принципа ISP - т.к. в данном примере
 * присутствует неверное выделение абстракции,
 * поскольку АКПП разных типов и назначения могут не иметь
 * всех указанных режимов.
 */
public interface ATTransmission {

    void getAuto();

    void getManual();

    void getComfort();

    void getEco();

    void getSport();

    void getRace();
}
