package dev.tandosid.playground.java.deadlock;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

@Disabled("This will generate a deadlock while running tests")
class CreateDeadlockTest {

    @Test
    public void shouldCreateDeadlock() {
        Printer printer = spy(new Printer() {
            @Override
            public void print(String string) {
                System.out.println(string);
            }
        });
        CreateDeadlock createDeadlock = new CreateDeadlock();
        createDeadlock.deadlock(printer);
    }

}