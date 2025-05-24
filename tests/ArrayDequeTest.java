import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {

     @Test
     @DisplayName("ArrayDeque has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }
    @Test
    public void add_first() {
        Deque<Integer> lld1 = new ArrayDeque<>();

        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.addFirst(4);
        assertThat(lld1.toList()).containsExactly(4, 3, 2).inOrder();
    }

    @Test
    public void add_last() {
        Deque<Integer> lld1 = new ArrayDeque<>();

        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addLast(4);
        assertThat(lld1.toList()).containsExactly(2, 3, 4).inOrder();
    }

    @Test
    public void add_first_nonempty() {
        Deque<Integer> lld1 = new ArrayDeque<>();
        lld1.addLast(0);
        lld1.addLast(1);
        lld1.addFirst(-1);
        lld1.addLast(2);
        lld1.addFirst(-2);

        assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
    }



    @Test
    public void add_last_nonempty() {
        Deque<Integer> lld1 = new ArrayDeque<>();

        lld1.addFirst(0);
        lld1.addFirst(0);
        lld1.addLast(3);

        assertThat(lld1.toList()).containsExactly(0, 0, 3).inOrder();
    }
    @Test
    public void add_first_from_empty() {
         Deque<Integer> lld1 = new ArrayDeque<>();
         lld1.addFirst(1);
         assertThat(lld1.toList()).containsExactly(1).inOrder();
    }
    @Test
    public void add_last_from_empty() {
         Deque<Integer> lld1 =  new ArrayDeque<>();
         lld1.addLast(1);
         assertThat(lld1.toList()).containsExactly(1).inOrder();
    }
    @Test
    public void add_first_trigger_resize() {
         Deque<Integer> lld1 = new ArrayDeque<>();
         for(int i = 0; i < 9; i++) {
             lld1.addFirst(i);
         }
         assertThat(lld1.size()).isEqualTo(9);
         assertThat(lld1.get(0)).isEqualTo(8);
    }
    @Test
    public void add_last_trigger_resize() {
         Deque<Integer> lld1 = new ArrayDeque<>();
         for (int i = 0; i < 9; i++) {
             lld1.addLast(i);
        }
         assertThat(lld1.size()).isEqualTo(9);
         assertThat(lld1.get(8)).isEqualTo(8);
    }
    @Test
    public void add_first_remove_to_empty(){
         Deque<Integer> lld1 = new ArrayDeque<>();
         lld1.addLast(1);
         lld1.addLast(3);
         lld1.addLast(3);
         lld1.removeLast();
         lld1.removeLast();
         lld1.removeLast();
         assertThat(lld1.isEmpty()).isTrue();
         lld1.addFirst(4);
         assertThat(lld1.size()).isEqualTo(1);
         assertThat(lld1.get(0)).isEqualTo(4);
    }
    @Test
    public void add_last_after_remove_to_empty(){
         Deque<Integer> lld1 = new ArrayDeque<>();
        lld1.addLast(1);
        lld1.addLast(3);
        lld1.addLast(3);
        lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();
        assertThat(lld1.isEmpty()).isTrue();
        lld1.addLast(4);
        assertThat(lld1.size()).isEqualTo(1);
        assertThat(lld1.get(0)).isEqualTo(4);

    }


    @Test
    public void get_valid() {
        Deque<Integer> lld1 = new ArrayDeque<>();

        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);

        assertThat(lld1.get(0)).isEqualTo(3);
        assertThat(lld1.get(1)).isEqualTo(2);
        assertThat(lld1.get(2)).isEqualTo(1);

    }

    @Test
    public void get_oob_large() {
        Deque<Integer> lld1 = new ArrayDeque<>();

        lld1.addFirst(5);
        lld1.addFirst(4);
        lld1.addFirst(3);

        assertThat(lld1.get(5)).isNull();
    }

    @Test
    public void get_oob_neg() {
        Deque<Integer> lld1 = new ArrayDeque<>();

        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);

        assertThat(lld1.get(-2)).isNull();
    }

    @Test
    public void test_Get_remove() {
        Deque<Integer> lld1 = new ArrayDeque<>();

        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.removeFirst();

        assertThat(lld1.get(2)).isNull();
    }

    @Test
    public void test_get_remove2() {
        Deque<Integer> lld1 = new ArrayDeque<>();

        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.removeFirst();

        assertThat(lld1.get(1)).isEqualTo(1);
    }

    @Test
    public void remove_first() {
        Deque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);

        int remove = lld1.removeFirst();
        assertThat(remove).isEqualTo(3);
        assertThat(lld1.toList()).containsExactly(2,1).inOrder();

    }
    @Test
    public void remove_last() {
         Deque<Integer> lld1 = new ArrayDeque<>();
         lld1.addLast(1);
         lld1.addLast(2);
         lld1.addLast(3);
         int remove = lld1.removeLast();
         assertThat(remove).isEqualTo(3);
         assertThat(lld1.toList()).containsExactly(1,2).inOrder();
    }


    @Test
    public void remove_first_to_empty() {
        Deque<Integer> lld1 = new ArrayDeque<>();

        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();

        assertThat(lld1.isEmpty()).isTrue();

    }

    @Test
    public void remove_last_to_empty() {
        Deque<Integer> lld1 = new ArrayDeque<>();

        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();

        assertThat(lld1.isEmpty()).isTrue();



    }

    @Test
    public void remove_first_to_one() {
        Deque<Integer> lld1 = new ArrayDeque<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.removeFirst();
        lld1.removeFirst();
        assertThat(lld1.size()).isEqualTo(1);
        assertThat(lld1.toList()).containsExactly(3).inOrder();;

    }

    @Test
    public void remove_last_to_one() {
        Deque<Integer> lld1 = new ArrayDeque<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.removeLast();
        lld1.removeLast();
        assertThat(lld1.size()).isEqualTo(1);
        assertThat(lld1.toList()).containsExactly(1).inOrder();;



    }

    @Test
    public void add_remove_add() {
        Deque<Integer> lld1 = new ArrayDeque<>();

        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.removeLast();
        lld1.removeLast();
        lld1.addLast(4);

        assertThat(lld1.toList()).containsExactly(3, 4).inOrder();

    }

    @Test
    public void add_remove_remove() {
        Deque<Integer> lld1 = new ArrayDeque<>();

        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.removeLast();
        lld1.addFirst(3);
        lld1.removeFirst();
        lld1.addFirst(4);

        assertThat(lld1.toList()).containsExactly(4, 2).inOrder();

    }
    @Test
    public void remove_first_trigger_resize() {
         Deque<Integer> lld1 = new ArrayDeque<>();
         for (int i = 0; i < 16; i++) {
             lld1.addLast(i);
         }
         for (int i = 0; i < 12; i++) {
             lld1.removeFirst();
         }
         lld1.removeFirst();
         assertThat(lld1.size()).isEqualTo(3);
    }
    @Test
    public void remove_last_trigger_resize(){
         Deque<Integer> lld1 = new ArrayDeque<>();
         for(int i = 0; i < 16; i++) {
             lld1.addLast(i);
         }
         for (int i = 0; i < 12; i++) {
             lld1.removeLast();
         }
         lld1.removeLast();
         assertThat(lld1.size()).isEqualTo(3);
    }

    @Test
    public void is_empty_true() {
        Deque<Integer> lld1 = new ArrayDeque<>();
        assertThat(lld1.isEmpty()).isTrue();
    }

    @Test
    public void is_empty_false() {
        Deque<Integer> lld1 = new ArrayDeque<>();

        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addLast(3);

        assertThat(lld1.isEmpty()).isFalse();
    }










    @Test
    public void size() {
        Deque<Integer> lld1 = new ArrayDeque<>();
        assertThat(lld1.size()).isEqualTo(0);
        lld1.addLast(1);
        assertThat(lld1.size()).isEqualTo(1);
        lld1.addLast(2);
        assertThat(lld1.size()).isEqualTo(2);
    }
    @Test
    public void size_after_remove_to_empty(){
        Deque<Integer> lld1 = new ArrayDeque<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);

        lld1.removeLast();
        assertThat(lld1.size()).isEqualTo(2);
        lld1.removeLast();
        assertThat(lld1.size()).isEqualTo(1);
        lld1.removeLast();
        assertThat(lld1.size()).isEqualTo(0);
    }
    @Test
    public void size_after_remove_from_empty() {
        Deque<Integer> lld1 = new ArrayDeque<>();
        assertThat(lld1.size()).isEqualTo(0);
        lld1.removeLast();
        assertThat(lld1.size()).isEqualTo(0);
    }
    @Test
    public void to_list_empty(){
        Deque<Integer> lld1 =  new ArrayDeque<>();
        assertThat(lld1.toList()).isEmpty();

    }
    @Test
    public void to_list_nonempty() {
        Deque<Integer> lld1 = new ArrayDeque<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        assertThat(lld1.toList()).containsExactly(1,2,3).inOrder();
    }










}
