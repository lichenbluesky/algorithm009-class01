##### 1.PriorityQueue介绍

PriorityQueue中每个元素都有一个权重，每次出队的元素一定是优先级最高或者优先级最低的元素。Java中PriorityQueue使用堆实现。

简单使用

```java
List list = Arrays.asList(1,2,3,9,4,3,6,0,0,1);
PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(list);while (! priorityQueue.isEmpty()) {   		
    System.err.print(priorityQueue.poll() + ",");                                          }
```

运行结果：

0,0,1,1,2,3,3,4,6,9,

##### 2.源代码分析

主要属性

```java
// 默认容量
private static final int DEFAULT_INITIAL_CAPACITY = 11;
// 实际存储元素的是数组
transient Object[] queue;
// 元素个数
private int size = 0; 
// 比较器
private final Comparator<? super E> comparator;
// 修改次数
transient int modCount = 0; 
```



主要方法：

入队：入队有两个方法，add(E e)和offer(E e)，两者是一致的，add(E e)最终也是调用的offer(E e)。

```java
public boolean offer(E e) {
    	// 判空
        if (e == null)
            throw new NullPointerException();
        modCount++;
        int i = size
        // 元素达到最大容量扩容
        if (i >= queue.length)
            grow(i + 1);
        size = i + 1;i
        if (i == 0)
            // i为0则放入数组第一个位置
            queue[0] = e;i
        else
            // i > 0则将元素插入第i个位置
            siftUp(i, e);
        return true;
}
```



扩容

```java
private void grow(int minCapacity) {
    // 旧容量
    int oldCapacity = queue.length;
    // 容量小于64则翻倍，容量大于64则增加50%
    int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                                     (oldCapacity + 2) :
                                     (oldCapacity >> 1));
    // 检查是否溢出
    if (newCapacity - MAX_ARRAY_SIZE > 0)
        newCapacity = hugeCapacity(minCapacity);
        
    // 创建出一个新容量大小的新数组并把旧数组元素拷贝过去
    queue = Arrays.copyOf(queue, newCapacity);
}

private static int hugeCapacity(int minCapacity) {
    if (minCapacity < 0) // overflow
        throw new OutOfMemoryError();
    return (minCapacity > MAX_ARRAY_SIZE) ?
        Integer.MAX_VALUE :
        MAX_ARRAY_SIZE;
}
```



删除元素:

```java
public boolean remove(Object o) {
    //获取元素的下标    
    int i = indexOf(o);
    if (i == -1)
        return false;
    else {
        removeAt(i);
        return true;
    }
}

/**
 * 执行移除操作
 */
private E removeAt(int i) {
    // assert i >= 0 && i < size;
    modCount++;
    int s = --size;
    // 如果要移除的就是最后一个元素，赋值为null
    if (s == i) // removed last element
        queue[i] = null;
    else {
        // 取队列尾元素
        E moved = (E) queue[s];
        // 将队尾元素置为null
        queue[s] = null;
        // 下沉操作
        siftDown(i, moved);
        // 如果下移后元素位置没发生变化，说明moved的左右子结点都大于moved，这时就需要上浮操作
        if (queue[i] == moved) {
            // 上浮操作
            siftUp(i, moved);
            // 如果上浮之后发生了元素位置
            if (queue[i] != moved)
                return moved;
        }
    }
    return null;
}
```



获取队首元素：peek只获取队首元素,实际并不出队, poll将队首元素出队.

```java
public E peek() {
    return (size == 0) ? null : (E) queue[0];
}

public E poll() {
    if (size == 0)
        return null;
    int s = --size;
    modCount++;
    // 堆中最小值
    E result = (E) queue[0];
    // 取数组中最后一个元素的值
    E x = (E) queue[s];
    // 将堆中最后一个值设置为null
    queue[s] = null;
    // 如果数组不是只有一个元素，执行下沉操作
    if (s != 0)
        // 下沉操作
        siftDown(0, x);
    return result;
}

private void siftDown(int k, E x) {
    if (comparator != null)
        siftDownUsingComparator(k, x);
    else
        siftDownComparable(k, x);
}

private void siftDownComparable(int k, E x) {
    Comparable<? super E> key = (Comparable<? super E>)x;
    // 计算非叶子结点元素的最大位置，循环的终止条件（在最后一个非叶子节点处结束）
    int half = size >>> 1;        // loop while a non-leaf
    while (k < half) {
        // 计算k位置处的左子结点
        int child = (k << 1) + 1; // assume left child is least
        Object c = queue[child];
        // 右子结点等于左子结点下标加1
        int right = child + 1;
        // 获取左右孩子中值较小的值 
        if (right < size &&
            ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
            c = queue[child = right];
        // 然后重新和父结点进行比较，如果大于父结点，不需要移动，结束
        if (key.compareTo((E) c) <= 0)
            break;
        // 父结点下移
        queue[k] = c;
        // 改变下标，循环此操作
        k = child;
    }
    queue[k] = key;
}
```





