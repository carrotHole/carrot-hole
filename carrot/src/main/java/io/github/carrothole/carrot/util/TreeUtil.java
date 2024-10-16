package io.github.carrothole.carrot.util;


import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author moon
 * @since 0.0.1
 */
public class TreeUtil {



    public static  <T> List<T> formatTree(String rootNode, List<T> list, Function<T,String> getId, Function<T,String> getParentId, Function<T,List<T>> getChildren, BiConsumer<T, List<T>> setChildren, Function<T,Integer> getSort){
        if (list.isEmpty()) return null;

        final ArrayList<T> resultList = new ArrayList<>();

        Map<String, T> map = new HashMap<>();
        // 做映射
        list.forEach(vo -> map.put(getId.apply(vo), vo));

        // 遍历
        list.forEach(vo -> {
            if (getParentId.apply(vo).equals(rootNode)){
                resultList.add(vo);
            }else {
                T parent = map.get(getParentId.apply(vo));
                List<T> children = getChildren.apply(parent);
                if (children == null){
                    children = new ArrayList<>();
                    setChildren.accept(parent,children);
                }
                children.add(vo);
            }
        });

        treeSort(resultList, getChildren, getSort);

        return resultList;
    }

    private static  <T> void treeSort(List<T> resultList, Function<T,List<T>> getChildren,  Function<T,Integer> getSort) {
        resultList.sort(Comparator.comparing(getSort));
        resultList.forEach(auMenuResultVO -> {
            List<T> children = getChildren.apply(auMenuResultVO);
            if (children != null){
                treeSort(children, getChildren, getSort);
            }
        });
    }

}
