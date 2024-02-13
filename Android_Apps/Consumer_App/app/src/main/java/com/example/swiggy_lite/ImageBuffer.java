package com.example.swiggy_lite;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ImageBuffer {
    private final int maxSize;
    private final Map<String, Integer> keyValueMap;
    private final PriorityQueue<Map.Entry<String, Integer>> priorityQueue;

    public ImageBuffer(int maxSize) {
        this.maxSize = maxSize;
        this.keyValueMap = new HashMap<>();
        this.priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
    }

    public void put(String key) {
        if (keyValueMap.containsKey(key)) {
            int count = keyValueMap.get(key) + 1;
            keyValueMap.put(key, count);
        } else {
            if (keyValueMap.size() >= maxSize) {
                Map.Entry<String, Integer> entryToRemove = priorityQueue.poll();
                keyValueMap.remove(entryToRemove.getKey());
            }
            keyValueMap.put(key, 1);
        }

        priorityQueue.clear();
        priorityQueue.addAll(keyValueMap.entrySet());
    }

    public Map<String, Integer> getBuffer() {
        return new HashMap<>(keyValueMap);
    }
}

