UTF-8编码格式下：
汉字一个字符占用三个字节
汉字标点同样占用三个字节
空格占用一个字节

详情参考：https://www.cnblogs.com/lslk89/p/6898526.html

对于NIO的Buffer空间分配的尝试解释
假设全部为汉字字符：
1.分配空间为15个字节，每次读写恰好是五个汉字，不存在剩余未读字节
2.分配空间为16个字节，每次读写是五个汉字，剩余一个未读字节，需要在下一次读取时候追加到buffer头部

NIO - Buffer简单介绍：
buffer是一块内存区域，由channel进入buffer称为写，由buffer进入channel称为读。buffer有三个属性：capacity（容量）、position（位置）、limit（限制）
【capacity】
当buffer构建时候指定的buffer大小，一旦分配该值就固定了；且在读写时候，一旦buffer打到了其capacity，就需要通过读数据或者清除数据，才能继续往里面写入数据

【position】
写数据时候，position标识当前数据位置 +1，初始位置为0。当有一个字节数据插入后，position会向下移动到下一个可插入数据的buffer单元位置。最大为capacity-1
读数据时候，position 置为0.当开始读取时候，position向下移动到下一个可以读取的元素的单元位置。

【limit】
写数据时候，limit标识可以向buffer中写入多少数据， limit = capacity
读数据时候，limit标识最多能读取的数据量（你能读到多少数据），limit 会被设置为之前写模式下position的值

[distance = limit - position]
写模式，buffer剩余可写空间
读模式，buffer剩余未读数据
单位为 byte

【Buffer的分配】
ByteBuffer buffer = ByteBuffer.allocate(48);

【向buffer中写数据】
- 从channel读到buffer
int bytesRead = inChannel.read(buffer); //read into buffer.
- buffer.put(put 方法有很多版本，允许你以不同的方式把数据写入到 Buffer 中)
buffer.put(127);

【从buffer中读数据】
- 从buffer写到channel
int bytesWritten = inChannel.write(buffer);
- buffer.get(get 方法有很多版本，允许你以不同的方式从 Buffer 中读取数据)
byte aByte = buffer.get();

【flip()、clear()和compact()、rewind()、mark()、reset()】
flip():切换写模式到读模式-将position设置为0，limit设置为之前position的值
clear():切换读模式为写模式-将position设置为0，limit设置为capacity的值。
        Buffer被清空，但是buffer中数据并未清除。只是打一个标记，告诉我们可以从哪里开始往buffer里面写入数据。
        如果buffer中有一些未读数据，使用clear，未读数据将被遗忘。
compact():将所有未读的数据拷贝到 Buffer 起始处。然后将 position 设到最后一个未读元素正后面。
        limit 属性依然像 clear() 方法一样，设置成 capacity。现在 Buffer 准备好写数据了，但是不会覆盖未读的数据。

rewind():将 position 设回 0，所以你可以重读 Buffer 中的所有数据。limit 保持不变，仍然表示能从 Buffer 中读取多少个元素。

【equals() 与 compareTo()】










