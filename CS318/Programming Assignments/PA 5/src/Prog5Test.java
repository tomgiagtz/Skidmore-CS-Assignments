/**
* Test program for the Cache Memory Simulation
*/
import java.util.*;
import java.io.*;

public class Prog5Test {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        int numReads = 10000; // number of read requests to perform

        // Create a MainMemory object
        MainMemory mainMemory = new MainMemory("example.data");
        int mainMemSize = mainMemory.getSize();
        System.out.println("Size of Main Memory in Bytes: " + mainMemSize);

        System.out.println("*********** Small Cache ***********");
        // Create a small set-associative cache
        // This is similar to the cache in Lab 8 Problem 3
        // 2 lines per set (this is a 2-way set associative cache)
        // 4 Bytes per line
        // Cache size is 32 Bytes
        CacheMemory smallCache = new CacheMemory(mainMemory, 32, 4, 2);
        smallTest(smallCache);
        System.out.println("Report from small cache test: ");
        smallCache.reportStats();
        System.out.println();

        // Create a set-associative cache
        // 4 lines per set (this is a 4-way set associative cache)
        // 16 Bytes per line
        // Cache size is 2 KiB (2048 Bytes)
        System.out.println();
        System.out.println("*********** Set-Associative Cache ***********");
        CacheMemory cacheMemoryA;
        cacheMemoryA = new CacheMemory(mainMemory, 2048, 16, 4);
        randomReads(cacheMemoryA, numReads, mainMemSize);
        System.out.println("Report from cacheMemoryA with randomReads:");
        cacheMemoryA.reportStats();
        System.out.println();

        cacheMemoryA = new CacheMemory(mainMemory, 2048, 16, 4);
        sequentialReads(cacheMemoryA, mainMemSize);
        System.out.println("Report from cacheMemoryA with sequentialReads:");
        cacheMemoryA.reportStats();
        System.out.println();

        cacheMemoryA = new CacheMemory(mainMemory, 2048, 16, 4);
        repeatReads(cacheMemoryA, numReads, mainMemSize);
        System.out.println("Report from cacheMemoryA with repeatReads:");
        cacheMemoryA.reportStats();
        System.out.println();

        // Create a direct mapped cache
        // 16 Bytes per line
        // Cache size is 2 KiB (2048 Bytes)
        System.out.println();
        System.out.println("*********** Direct Mapped Cache ***********");
        CacheMemory cacheMemoryB;
        cacheMemoryB = new CacheMemory(mainMemory, 2048, 16, 1);
        randomReads(cacheMemoryB, numReads, mainMemSize);
        System.out.println("Report from cacheMemoryB with randomReads:");
        cacheMemoryB.reportStats();
        System.out.println();

        cacheMemoryB = new CacheMemory(mainMemory, 2048, 16, 1);
        sequentialReads(cacheMemoryB, mainMemSize);
        System.out.println("Report from cacheMemoryB with sequentialReads:");
        cacheMemoryB.reportStats();
        System.out.println();

        cacheMemoryB = new CacheMemory(mainMemory, 2048, 16, 1);
        repeatReads(cacheMemoryB, numReads, mainMemSize);
        System.out.println("Report from cacheMemoryB with repeatReads:");
        cacheMemoryB.reportStats();
        System.out.println();

        // Create a fully associative cache
        // 512 lines per set (all lines in 1 set)
        // 16 Bytes per line
        // Cache size is 2 KiB (2048 Bytes)
        System.out.println();
        System.out.println("*********** Fully Associative Cache ***********");
        CacheMemory cacheMemoryC;
        cacheMemoryC = new CacheMemory(mainMemory, 2048, 16, 128);
        randomReads(cacheMemoryC, numReads, mainMemSize);
        System.out.println("Report from cacheMemoryC with randomReads:");
        cacheMemoryC.reportStats();
        System.out.println();

        cacheMemoryC = new CacheMemory(mainMemory, 2048, 16, 128);
        sequentialReads(cacheMemoryC, mainMemSize);
        System.out.println("Report from cacheMemoryC with sequentialReads:");
        cacheMemoryC.reportStats();
        System.out.println();

        cacheMemoryC = new CacheMemory(mainMemory, 2048, 16, 128);
        repeatReads(cacheMemoryC, numReads, mainMemSize);
        System.out.println("Report from cacheMemoryC with repeatReads:");
        cacheMemoryC.reportStats();
        System.out.println();

    } // end of main method

    /**
    * A small test of the cache memory.
    * Similar to Lab 8 Problem 3.
    * @param cache The CacheMemory object to read from.
    */
    public static void smallTest(CacheMemory cache) {
        boolean[] address;

        // Memory address in Lab 8 is the line address.
        // Add two bits to the least significant side of the Lab 8
        // address to get the memory Byte address.

        address = Binary.uDecToBin(4L); // Operation 1: address 000100
        cache.read32(address);

        address = Binary.uDecToBin(16L); // Operation 2: address 010000
        cache.read32(address);

        address = Binary.uDecToBin(24L); // Operation 3: address 011000
        cache.read32(address);

        address = Binary.uDecToBin(40L); // Operation 4: address 101000
        cache.read32(address);

        address = Binary.uDecToBin(60L); // Operation 5: address 111100
        cache.read32(address);

        address = Binary.uDecToBin(32L); // Operation 6: address 100000
        cache.read32(address);

        address = Binary.uDecToBin(0L); // Operation 7: address 000000
        cache.read32(address);

        address = Binary.uDecToBin(52L); // Operation 8: address 110100
        cache.read32(address);

        address = Binary.uDecToBin(28L); // Operation 9: address 011100
        cache.read32(address);

        address = Binary.uDecToBin(28L); // Operation 10: address 011100
        cache.read32(address);

        address = Binary.uDecToBin(32L); // Operation 11: address 100000
        cache.read32(address);

        address = Binary.uDecToBin(44L); // Operation 12: address 101100
        cache.read32(address);

        address = Binary.uDecToBin(0L); // Operation 13: address 000000
        cache.read32(address);

        address = Binary.uDecToBin(60L); // Operation 14: address 111100
        cache.read32(address);

    }

    /**
    * Calls the CacheMemory read32 method with random addresses
    *
    * @param cache The CacheMemory object to read from.
    * @param numReads The number of read operations to perform.
    * @param memSize The size of main memory
    */
    public static void randomReads(CacheMemory cache, int numReads, int memSize) {

        int wordsInMainMem = (memSize / 4) - 4;
        Random random = new Random(123);

        for(int i = 0; i < numReads; i++) {
            // Generate a random word address
            int wordAddress = random.nextInt(wordsInMainMem) * 4;
            boolean[] address = Binary.uDecToBin(wordAddress);
            cache.read32(address);
        }

    }

    /**
    * Sequentially reads the memory
    *
    * @param cache The CacheMemory object to read from.
    * @param memSize The size of main memory
    */
    public static void sequentialReads(CacheMemory cache, int memSize) {

        int wordsInMainMem = memSize / 4;
        int addressInt = 0;
        boolean[] address;

        for(int i = 0; i < memSize - 16; i+=4) {
            address = Binary.uDecToBin(i);
            cache.read32(address);
        }
    }

    /**
    * Calls the CacheMemory read32 method with some repeated addresses
    *
    * @param cache The CacheMemory object to read from.
    * @param numReads The number of read operations to perform.
    * @param memSize The size of main memory
    */
    public static void repeatReads(CacheMemory cache, int numReads, int memSize) {

        int wordsInMainMem = (memSize / 4) - 4;
        Random random = new Random(456);

        int wordAddress;

        // Set some addresses for repeated reads
        wordAddress = random.nextInt(wordsInMainMem) * 4;
        boolean[] addressA = Binary.uDecToBin(wordAddress);

        wordAddress = random.nextInt(wordsInMainMem) * 4;
        boolean[] addressB = Binary.uDecToBin(wordAddress);

        wordAddress = random.nextInt(wordsInMainMem) * 4;
        boolean[] addressC = Binary.uDecToBin(wordAddress);

        wordAddress = random.nextInt(wordsInMainMem) * 4;
        boolean[] addressD = Binary.uDecToBin(wordAddress);


        for(int i = 0; i < numReads; i++) {

            // Every 10 reads, read the repeat addresses
            if(i % 10 == 0) {
                cache.read32(addressA);
                cache.read32(addressB);
                cache.read32(addressC);
                cache.read32(addressD);
            } else {
                // Generate a random word address
                wordAddress = random.nextInt(wordsInMainMem) * 4;
                boolean[] address = Binary.uDecToBin(wordAddress);
                cache.read32(address);
            }

        }

    }

}
