package containers;

import java.util.*;

/**
 *  Использование классов Abstract
 *  <p>
 *  Следующее решение демонстрирует другой паттерн проектирования: «Легковес». Он применяется в тех случаях,
 *  когда обычное решение требует слишком большого количества объектов или создание обычных объектов требует
 *  слишком больших затрат памяти.
 *  <p>
 *  Чтобы создать контейнер Мар, доступный только для чтения, вы наследуете от AbstractMap и реализуете entrySet().
 *  Чтобы создать множество Set, доступное только для чтения, разработчик определяет класс, производный от AbstractSet,
 *  и реализует iterator() и size().
 *  <p>
 *  Собственно «легковесная» часть: в каждом объекте Map.Entry хранится индекс вместо фактического ключа и значения.
 *  <p>
 *  Другая часть паттерна реализуется в EntrySet.Iterator. Вместо создания объекта Map. Entry для каждой пары данных
 *  в DATA создается только один объект Map.Entry на итератор. Объект Entry используется как «окно» к данным;
 *  в нем содержится только индекс (index) в статическом массиве строк. При каждом вызове next() для итератора индекс
 *  в Entry увеличивается, чтобы он указывал на следующую пару, после чего next() возвращает объект Entry этого итератора.
 */
public class Countries {
    public static final String[][] DATA = {
            // Африка
            {"ALGERIA","Algiers"}, {"ANGOLA","Luanda"},
            {"BENIN","Porto-Novo"}, {"BOTSWANAV", "Gaberone"},
            {"BULGARIA","Sofia"}, {"BURKINA FAS0","0uagadOUgou"},
            {"BURUNDI","Bujumbura"},
            {"CAMEROON","Yaounde"}, {"CAPE VERDE","Praia"},
            {"CENTRAL AFRICAN REPUBLIC","Bangui"},
            {"CHAD","N‘djamena"}, {"COMOROS","Moroni"},
            {"CONGO","Brazzaville"}, {"D3IB0UTI","Dijibouti"},
            {"EGYPT", "Cairo"}, {"EQUATORML GUINEA", "Malabo"},
            {"ERITREA","Asmara"}, {"ETHIOPIA","Addis Ababa"},
            {"GABON","Libreville"}, {"THE GAMBIA", "Banjul"},
            {"GHANA","Accra"}, {"GUINEA","Conakry"}, {"BISSAU","Bissau"},
            {"СОТЕ D’lVOIR (IVORY COAST)","Yamoussoukro"},
            {"KENYA","Nairobi"}, {"LESOTHO","Maseru"},
            {"LIBERIA","Monrovia"}, {"LIBYA","Tripoli"},
            {"MADAGASCAR","Antananarivo"}, {"MALAWI","Lilongwe"},
            {"MALI","Bamako"}, {"MAURITANIA","Nouakchott"},
            {"MAURITIUS","Port Louis"}, {"MOROCCO","Rabat"},
            {"MOZAMBIQUE","Maputo"}, {"NAMIBIA","Windhoek"},
            {"NIGER","Niamey"}, {"NIGERIA","Abuja"},
            {"RWANDA","Kigali"},
            {"SAO TOME E PRINCIPE","Sao Tome"},
            {"SENEGAL","Dakar"}, {"SEYCHELLES","Victoria"},
            {"SIERRA LEONE","Freetown"}, {"SOMALIA","Mogadishu"},
            {"SOUTH AFRICA","Pretoria/Cape Town"},
            {"SUDAN","Khartoum"},
            {"SWAZILAND","Mbabane"}, {"TANZANIA","Dodoma"},
            {"TOGO","Lome"}, {"TUNISIA","Tunis"},
            {"UGANDA","Kampala"},
            {"DEMOCRATIC REPUBLIC OF THE CONGO (ZAIRE)", "Kinshasa"},
            {"ZAMBIA","Lusaka"}, {"ZIMBABWE","Harare"},
            // Азия
            {"AFGHANISTAN","Kabul"}, {"BAHRAIN","Manama"},
            {"BANGLADESH","Dhaka"}, {"BHUTAN","Thimphu"},
            {"BRUNEI","Bandar Seri Begawan"},
            {"CAMBODIA","Phnom Penh"},
            {"CHINA","Beijing"}, {"CYPRUS","Nicosia"},
            {"INDIA","New Delhi"}, {"INDONESIA","Jakarta"},
            {"IRAN","Tehran"}, {"IRAQ","Baghdad"},
            {"ISRAEL","lerusalem"}, {"JAPAN","Tokyo"},
            {"30RDAN","Amman"}, {"KUWAIT","Kuwait City"},
            {"LAOS","Vientiane"}, {"LEBANON","Beirut"},
            {"MALAYSIA","Kuala Lumpur"}, {"THE MALDIVES","Male"},
            {"MONGOLIA","Ulan Bator"},
            {"MYANMAR (BURMA)","Rangoon"},
            {"NEPAL","Katmandu"}, {"NORTH KOREA","P'yongyang"},
            {"OMAN","Muscat"}, {"PAKISTAN","Islamabad"},
            {"PHILIPPINES","Manila"}, {"QATAR","Doha"},
            {"SAUDI ARABIA","Riyadh"}, {"SINGAPORE","Singapore"},
            {"SOUTH KOREA","Seoul"}, {"SRI LANKA","Colombo"},
            {"SYRIA","Damascus"},
            {"TAIWAN (REPUBLIC OF CHINA)","Taipei"},
            {"THAILAND","Bangkok"}, {"TURKEY","Ankara"},
            {"UNITED ARAB EMIRATES","Abu Dhabi"},
            {"VIETNAM","Hanoi"}, {"YEMEN","Sana’a"},
            // Австралия и Океания
            {"AUSTRALIA","Canberra"}, {"FIDI","Suva"},
            {"KIRIBATI","Bairiki"},
            {"MARSHALL ISLANDS","Dalap-Uliga-Darrit"},
            {"MICRONESIA","Palikir"}, {"NAURU","Yaren"},
            {"NEW ZEALAND","Wellington"}, {"PALAU","Koror"},
            {"PAPUA NEW GUINEA","Port Moresby"},
            {"SOLOMON ISLANDS","Honaira"}, {"TONGA","Nuku'alofa"},
            {"TUVALU","Fongafale"}, {"VANUATU","< Port-Vila"},
            {"WESTERN SAMOA","Apia"},
            // Восточная Европа и страны бывшего СССР
            {"ARMENIA","Yerevan"}, {"AZERBAIDAN","Baku"},
            {"BELARUS (BYELORUSSIA)","Minsk"},
            {"GEORGIA","Tbilisi"},
            {"KAZAKSTAN","Almaty"}, {"KYRGYZSTAN","Alma-Ata"},
            {"MOLDOVA","Chisinau"}, {"RUSSIA","Moscow"},
            {"TA3IKISTAN","Dushanbe"}, {"TURKMENISTAN","Ashkabad"},
            {"UKRAINE","Kyiv"}, {"UZBEKISTAN","Tashkent"},
            // Европа
            {"ALBANIA","Tirana"}, {"ANDORRA","Andorra la Vella"},
            {"AUSTRIA","Vienna"}, {"BELGIUM","Brussels"},
            {"BOSNIA","-"}, {"HERZEGOVINA","Sarajevo"},
            {"CROATIA","Zagreb"}, {"CZECH REPUBLIC","Prague"},
            {"DENMARK","Copenhagen"}, {"ESTONIA","Tallinn"},
            {"FINLAND","Helsinki"}, {"FRANCE","Paris"},
            {"GERMANY","Berlin"}, {"GREECE","Athens"},
            {"HUNGARY","Budapest"}, {"ICELAND","Reykjavik"},
            {"IRE LAND","Dublin"}, {"ITALY","Rome"},
            {"LATVIA","Riga"}, {"LIECHTENSTEIN","Vaduz"},
            {"LITHUANIA","Vilnius"}, {"LUXEMBOURG","Luxembourg"},
            {"MACEDONIA","Skopje"}, {"MALTA","Valletta"},
            {"MONACO","Monaco"}, {"MONTENEGRO","Podgorica"},
            {"THE NETHERLANDS","Amsterdam"}, {"NORWAY","Oslo"},
            {"POLAND","Wa rsaw"}, {"PORTUGAL","Lis bon"},
            {"ROMANIA","Bucharest"}, {"SAN MARINO","San Marino"},
            {"SERBIA","Belgrade"}, {"SLOVAKIA","Bratislava"},
            {"SLOVENIA","Ljuijana"}, {"SPAIN","Madrid"},
            {"SWEDEN","Stockholm"}, {"SWITZERLAND","Berne"},
            {"UNITED KINGDOM","London"}, {"VATICAN CITY","---"},
            // Северная и Центральная Америка
            {"ANTIGUA AND BARBUDA","Saint 3ohn's"},
            {"BAHAMAS","Nassau"},
            {"BARBADOS","Bridgetown"}, {"BELIZE","Belmopan"},
            {"CANADA","Ottawa"}, {"COSTA RICA","San 3ose"},
            {"CUBA","Havana"}, {"DOMINICA","Roseau"},
            {"DOMINICAN REPUBLIC","Santo Domingo"},
            {"EL SALVADOR","San Salvador"},
            {"GRENADA","Saint George's"},
            {"GUATEMALA","Guatemala City"},
            {"HAITI","Port-au-Prince"},
            {"HONDURAS","Tegucigalpa"}, {"DAMAICA","Kingston"},
            {"MEXICO","Mexico City"}, {"NICARAGUA","Managua"},
            {"PANAMA","Panama City"}, {"ST. KITTS","-"},
            {"NEVIS","Basseterre"}, {"ST. LUCIA","Castries"},
            {"ST. VINCENT AND THE GRENADINES","Kingstown"},
            {"UNITED STATES OF AMERICA","Washington, D.C."},
            // Южная Америка
            {"ARGENTINA","Buenos Aires"},
            {"BOLIVIA","Sucre (legal)/La Paz(administrative)"},
            {"BRAZIL","Brasilia"}, {"CHILE","Santiago"},
            {"COLOMBIA","Bogota"}, {"ECUADOR","Quito"},
            {"GUYANA","Georgetown"}, {"PARAGUAY","Asuncion"},
            {"PERU","Lima"}, {"SURINAME","Paramaribo"},
            {"TRINIDAD AND TOBAGO","Port of Spain"},
            {"URUGUAY","Montevideo"}, {"VENEZUELA","Caracas"},
    };

    // Использование AbstractMap реализацией entrySet()
    private static class FlyweightMap extends AbstractMap<String,String> {
        private static class Entry implements Map.Entry<String,String> {
            int index;
            Entry(int index) { this.index = index; }
            public boolean equals(Object o) { return DATA[index][0].equals(o); }
            public String getKey() { return DATA[index][0]; }
            public String getValue() { return DATA[index][1]; }
            public String setValue (String value) { throw new UnsupportedOperationException(); }
            public int hashCode () { return DATA[index][0].hashCode(); }
        }

        // Использование AbstractSet реализацией size() и iterator()
        static class EntrySet extends AbstractSet<Map.Entry<String,String>> {
            private int size;
            EntrySet(int size) {
                if (size < 0)
                    this.size = 0;
                    // Не может быть больше массива:
                else if (size > DATA.length)
                    this.size = DATA.length;
                else
                    this.size = size;
            }

            public int size() { return size; }
            private class Iter implements Iterator<Map.Entry<String, String>> {
                // Только один объект Entry на Iterator:
                private Entry entry = new Entry(-1);

                public boolean hasNext() { return entry.index < size - 1; }

                public Map.Entry<String, String> next() {
                    entry.index++;
                    return entry;
                }

                public void remove() { throw new UnsupportedOperationException(); }
            }

            public Iterator<Map.Entry<String, String>> iterator() { return new Iter(); }
        }
        private static Set<Map.Entry<String,String>> entries = new EntrySet(DATA.length);
        public Set<Map.Entry<String,String>> entrySet() { return entries; }
    }

    // Создание частичной карты из 'size' стран:
    static Map<String,String> select(final int size) {
        return new FlyweightMap() {
            public Set<Map.Entry<String, String>> entrySet() { return new EntrySet(size); }
        };
    }
    static Map<String,String> map = new FlyweightMap();
    public static Map<String,String> capitals() {
        return map; // Полная карта
    }
    public static Map<String,String> capitals(int size) {
        return select(size); // Частичная карта
    }
    static List<String> names = new ArrayList<String>(map.keySet());
    // Все имена:
    public static List<String> names() { return names; }
    // Частичный список:
    public static List<String> names(int size) {
        return new ArrayList<String>(select(size).keySet());
    }
    public static void main(String[] args) {
        System.out.println(capitals(10));
        System.out.println(names(10));
        System.out.println(new HashMap<String, String>(capitals(3)));
        System.out.println(new LinkedHashMap<String, String>(capitals(3)));
        System.out.println(new TreeMap<String, String>(capitals(3)));
        System.out.println(new Hashtable<String, String>(capitals(3)));
        System.out.println(new HashSet<String>(names(6)));
        System.out.println(new LinkedHashSet<String>(names(6)));
        System.out.println(new TreeSet<String>(names(6)));
        System.out.println(new ArrayList<String>(names(6)));
        System.out.println(new LinkedList<String>(names(6)));
        System.out.println(capitals().get("BRAZIL"));
    }
}

class CountingIntegerList extends AbstractList<Integer> {
    private int size;
    public CountingIntegerList(int size) { this.size = size < 0 ? 0 : size; }
    public Integer get(int index) { return Integer.valueOf(index); }
    public int size() { return size; }
    public static void main(String[] args) {
        System.out.println(new CountingIntegerList(30));
    }
}