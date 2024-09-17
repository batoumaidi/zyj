package com.ityls.shoping_goods_service.service;

import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ityls.shoping_common.pojo.Goods;
import com.ityls.shoping_common.pojo.GoodsImage;
import com.ityls.shoping_common.pojo.Specification;
import com.ityls.shoping_common.pojo.SpecificationOption;
import com.ityls.shoping_common.service.GoodsService;
import com.ityls.shoping_goods_service.mapper.GoodsImageMapper;
import com.ityls.shoping_goods_service.mapper.GoodsMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@DubboService
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsImageMapper goodsImageMapper;

    @Override
    public void add(Goods goods) {
        // 插入商品数据
        goodsMapper.insert(goods);

        // 插入图片数据
        Long goodsId = goods.getId(); // 获取商品主键
        List<GoodsImage> images = goods.getImages(); // 商品图片
        for (GoodsImage image : images) {
            image.setGoodsId(goodsId); // 给图片设置商品id
            goodsImageMapper.insert(image); //插入图片
        }

        // 插入商品_规格项数据
        // 1.获取规格
        List<Specification> specifications = goods.getSpecifications();
        // 2.获取规格项
        List<SpecificationOption> options = new ArrayList(); //规格项集合
        // 遍历规格，获取规格中的所有规格项
        for (Specification specification : specifications) {
            options.addAll(specification.getSpecificationOptions());
        }
        // 3.遍历规格项，插入商品_规格项数据
        for (SpecificationOption option : options) {
            goodsMapper.addGoodsSpecificationOption(goodsId, option.getId());
        }
    }

    @Override
    public void update(Goods goods) {
        // 删除旧图片数据
        Long goodsId = goods.getId(); // 商品id
        QueryWrapper<GoodsImage> queryWrapper = new QueryWrapper();
        queryWrapper.eq("goodsId", goodsId);
        goodsImageMapper.delete(queryWrapper);
        // 删除旧规格项数据
        goodsMapper.deleteGoodsSpecificationOption(goodsId);

        // 插入商品数据
        goodsMapper.updateById(goods);
        // 插入图片数据
        List<GoodsImage> images = goods.getImages(); // 商品图片
        for (GoodsImage image : images) {
            image.setGoodsId(goodsId); // 给图片设置商品id
            goodsImageMapper.insert(image); // 插入图片
        }
        // 插入商品_规格项数据
        List<Specification> specifications = goods.getSpecifications(); // 获取规格
        List<SpecificationOption> options = new ArrayList(); // 规格项集合
        // 遍历规格，获取规格中的所有规格项
        for (Specification specification : specifications) {
            options.addAll(specification.getSpecificationOptions());
        }
        // 遍历规格项，插入商品_规格项数据
        for (SpecificationOption option : options) {
            goodsMapper.addGoodsSpecificationOption(goodsId, option.getId());
        }
    }

    @Override
    public Goods findById(Long id) {
        return goodsMapper.findById(id);
    }

    @Override
    public void putAway(Long id, Boolean isMarketable) {
        goodsMapper.putAway(id, isMarketable);
    }

    @Override
    public Page<Goods> search(Goods goods, int page, int size) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper();
        // 判断商品名不为空
        if (goods != null && StringUtils.hasText(goods.getGoodsName())){
            queryWrapper.like("goodsName",goods.getGoodsName());
        }
        Page<Goods> page1 = goodsMapper.selectPage(new Page(page, size), queryWrapper);
        return page1;
    }
}