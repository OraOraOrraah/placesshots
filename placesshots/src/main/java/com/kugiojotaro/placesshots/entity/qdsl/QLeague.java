package com.kugiojotaro.placesshots.entity.qdsl;

import static com.mysema.query.types.PathMetadataFactory.*;
import com.kugiojotaro.placesshots.entity.League;


import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QLeague is a Querydsl query type for League
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLeague extends EntityPathBase<League> {

    private static final long serialVersionUID = -2146947856L;

    public static final QLeague league = new QLeague("league");

    public final NumberPath<Short> id = createNumber("id", Short.class);

    public final StringPath title = createString("title");

    public QLeague(String variable) {
        super(League.class, forVariable(variable));
    }

    public QLeague(Path<? extends League> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLeague(PathMetadata<?> metadata) {
        super(League.class, metadata);
    }

}

