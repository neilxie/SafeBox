package com.max.security.utils;

import com.max.security.App;
import com.max.security.greendao.FileModel;
import com.max.security.greendao.FileModelDao;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/9/1.
 */
public class ObservableUtils {

    public static Observable<List<FileModel>> getFileModelByType(final int type) {
        return Observable.create(new Observable.OnSubscribe<List<FileModel>>() {

            @Override
            public void call(Subscriber<? super List<FileModel>> subscriber) {
                FileModelDao dao = App.getFileModelDao();
                List<FileModel> list = dao.queryBuilder()
                        .where(FileModelDao.Properties.FileType.eq(type))
                        .orderAsc(FileModelDao.Properties.CreateTime)
                        .build()
                        .list();
                subscriber.onNext(list);
                subscriber.onCompleted();
            }
        });
    }
}
