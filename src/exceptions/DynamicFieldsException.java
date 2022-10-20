package exceptions;

/**
 *  Если вам понадобится организовать цепочку из других типов исключений,
 *  придется использовать метод initCause(),
 *
 *  Если вы поместите в поле пустую ссылку null, то это возбудит исключение
 *  DynamicFieldsException, создавая объект нужного типа и передавая его методу
 *  initCause() в качестве причины исключение NullPointerException.
 */

public class DynamicFieldsException extends Exception {
    public static class DynamicFields {
        private Object[][] fields;
        public DynamicFields(int initialSize) {
            fields = new Object[initialSize][2];
            for(int i = 0; i < initialSize; i++)
                fields[i] = new Object[] { null, null };
        }
        public String toString() {
            StringBuilder result = new StringBuilder();
            for(Object[] obj : fields) {
                result.append(obj[0]);
                result.append(": ");
                result.append(obj[1]);
                result.append("\n");
            }
            return result.toString();
        }
        private int hasField(String id) {
            for(int i = 0; i < fields.length; i++)
                if(id.equals(fields[i][0]))
                    return i;
            return -1;
        }
        private int
        getFieldNumber(String id) throws NoSuchFieldException {
            int fieldNum = hasField(id);
            if (fieldNum == -1)
                throw new NoSuchFieldException();
            return fieldNum;
        }
        private int makeField(String id) {
            for(int i = 0; i < fields.length; i++)
                if(fields[i][0] == null) {
                    fields[i][0] = id;
                    return i;
                }
// Пустых полей нет, добавить новое:
            Object[][] tmp = new Object[fields.length + 1][2];
            for(int i = 0; i < fields.length; i++)
                tmp[i] = fields[i];
            for(int i = fields.length; i < tmp.length; i++)
                tmp[i] = new Object[] { null, null };
            fields = tmp;
// Рекурсивный вызов с новыми полями:
            return makeField(id);
        }
        public Object
        getField(String id) throws NoSuchFieldException {
            return fields[getFieldNumber(id)][1];
        }
        public Object setField(String id, Object value) throws DynamicFieldsException {
            if (value == null) {
// У большинства исключений нет конструктора,
// принимающего объект-причину.
// В таких случаях следует использовать
// метод initCause(), доступный всем
// подклассам Throwable.
                DynamicFieldsException dfe = new DynamicFieldsException();

                dfe.initCause(new NullPointerException());
                throw dfe;
            }
            int fieldNumber = hasField(id);
            if (fieldNumber == -1)
                fieldNumber = makeField(id);
            Object result = null;
            try {
                result = getField(id); // Получаем старое значение
            } catch (NoSuchFieldException e) {
// Используем конструктор с "причиной":
                throw new RuntimeException(e);
            }
            fields[fieldNumber][1] = value;
            return result;
        }
        public static void main(String[] args) {
            DynamicFields df = new DynamicFields(3);
            System.out.println(df);
            try {
                df.setField("d", "Значение d");
                df.setField("number", 47);
                df.setField("number2", 48);
                System.out.println(df);
                df.setField("d", "Новое значение d");
                df.setField("number3", 11);
                System.out.println("df: " + df);
                System.out.println("df.getField(\"d\") : " + df.getField("d"));
                Object field = df.setField("d", null); // Исключение
            } catch (NoSuchFieldException e) {
                e.printStackTrace(System.out);
            } catch (DynamicFieldsException e) {
                e.printStackTrace(System.out);
            }
        }
    }
}