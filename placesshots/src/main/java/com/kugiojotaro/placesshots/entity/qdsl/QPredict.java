package com.kugiojotaro.placesshots.entity.qdsl;

import static com.mysema.query.types.PathMetadataFactory.*;
import com.kugiojotaro.placesshots.entity.Predict;


import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPredict is a Querydsl query type for Predict
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPredict extends EntityPathBase<Predict> {

    private static final long serialVersionUID = 1794912824L;

    public static final QPredict predict = new QPredict("predict");

    public final NumberPath<Short> awayScore = createNumber("awayScore", Short.class);

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    protected QFixture fixture;
    //public final NumberPath<Long> fixture = createNumber("fixture", Long.class);

    public final NumberPath<Short> homeScore = createNumber("homeScore", Short.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Short> point = createNumber("point", Short.class);

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QPredict(String variable) {
        super(Predict.class, forVariable(variable));
    }

    public QPredict(Path<? extends Predict> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPredict(PathMetadata<?> metadata) {
        super(Predict.class, metadata);
    }

    public QFixture fixture() {
        if (fixture == null) {
        	fixture = new QFixture(forProperty("fixture"));
        }
        return fixture;
    }
    
}

