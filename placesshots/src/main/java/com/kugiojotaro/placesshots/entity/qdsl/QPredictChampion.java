package com.kugiojotaro.placesshots.entity.qdsl;

import static com.mysema.query.types.PathMetadataFactory.*;
import com.kugiojotaro.placesshots.entity.PredictChampion;


import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPredictChampion is a Querydsl query type for PredictChampion
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPredictChampion extends EntityPathBase<PredictChampion> {

    private static final long serialVersionUID = -724311359L;

    public static final QPredictChampion predictChampion = new QPredictChampion("predictChampion");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath round = createString("round");

    public final NumberPath<Integer> teamId = createNumber("teamId", Integer.class);

    public final StringPath user = createString("user");

    public QPredictChampion(String variable) {
        super(PredictChampion.class, forVariable(variable));
    }

    public QPredictChampion(Path<? extends PredictChampion> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPredictChampion(PathMetadata<?> metadata) {
        super(PredictChampion.class, metadata);
    }

}

