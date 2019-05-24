package com.example.jmssubdemo;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class QueueTest {

    @Test
    public void name() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        Integer poll = queue.poll();
        queue.offer(5);
        Integer peek = queue.peek();

        assertThat(poll).isEqualTo(1);
        assertThat(peek).isEqualTo(2);
        assertThat(queue.size()).isEqualTo(4);

    }
}
