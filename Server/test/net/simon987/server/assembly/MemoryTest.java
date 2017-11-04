package net.simon987.server.assembly;

import net.simon987.server.ServerConfiguration;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;


public class MemoryTest {
    @Test
    public void getSet() {
        ServerConfiguration config = new ServerConfiguration(new File("config.properties"));
        int memorySize = config.getInt("memory_size");
        Memory memory = new Memory(memorySize);

        memory.set(1, 1);
        assertEquals(1, memory.get(1));

        memory.set(memorySize / 2 - 1, 1);
        assertEquals(1, memory.get(memorySize / 2 - 1));

        memory.get(memorySize / 2);
        memory.get(-1);

        memory.set(memorySize / 2, 1);
        memory.set(-1, 1);
    }

    @Test
    public void write() {

        ServerConfiguration config = new ServerConfiguration(new File("config.properties"));
        int memorySize = config.getInt("memory_size");
        Memory memory = new Memory(memorySize);


        assertTrue(memory.write(0, new byte[memorySize], memorySize));
        assertFalse(memory.write(0, new byte[memorySize], memorySize + 1));
        assertFalse(memory.write(0, new byte[memorySize], -1));
        assertFalse(memory.write(-1, new byte[memorySize], 10));

        assertFalse(memory.write(memorySize / 2, new byte[15], 1));
        assertFalse(memory.write((memorySize / 2) - 5, new byte[11], 11));
        assertTrue(memory.write((memorySize / 2) - 5, new byte[11], 10));

    }


}