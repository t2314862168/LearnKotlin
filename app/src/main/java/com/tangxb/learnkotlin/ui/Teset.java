package com.tangxb.learnkotlin.ui;

import com.tangxb.learnkotlin.bean.BannerBean;
import com.tangxb.learnkotlin.bean.BaseBean;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class Teset {
    void test1() {
        BaseBean<BannerBean> baseBean = new BaseBean<>();
        Observable.just(baseBean)
                .onErrorReturn(new Function<Throwable, BaseBean<BannerBean>>() {
                    @Override
                    public BaseBean<BannerBean> apply(Throwable throwable) throws Exception {
                        BaseBean<BannerBean> baseBean2 = new BaseBean<>();
                        baseBean2.setThrowable(throwable);
                        return baseBean2;
                    }
                });
    }
}
