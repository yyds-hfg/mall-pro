package com.hacker.commom.utils;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: Zero
 * @Date: 2022/4/22 23:41
 * @Description: 切割容器的子集
 */
public class ListSplitter<T> {

    private final List<T> list;
    private final int chunkSize; //把list按这个大小进行分割
    private int index;    //当前索引

    /**
     * 构建切割容器
     * @param list list集合
     * @param chunkSize 每一块的大小
     */
    public ListSplitter(@NotNull(message = "被切割集合不能为null") List<T> list,
                        @Min(value = 1,message = "每一块大小必须大于等于一")
                                int chunkSize){
        if (chunkSize>list.size()) {
            throw new IllegalArgumentException("每一块大小不能超过list长度");
        }
        this.list = list;
        this.chunkSize = chunkSize;
        this.index = 0;
    }

    /**
     * 判断list是否还有元素
     * @return 有 true
     */
    public boolean hasNext() {
        return index < list.size();
    }

    /**
     * @return 返回分割之后的list的子集合
     */
    public List<T> next(){
        int endIndex= Math.min(index + chunkSize, list.size());
        List<T> ret= list.subList(index, endIndex);
        index = endIndex;
        return ret;
    }

    /**
     * 得到当前索引
     * @return index
     */
    public int getIndex(){
        return index;
    }

    /**
     * 得到list集合大小
     * @return size
     */
    public int getSize(){
        return list.size();
    }
}
