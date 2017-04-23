package com.kugiojotaro.placesshots.entity.qdsl;

import static com.mysema.query.types.PathMetadataFactory.*;
import com.kugiojotaro.placesshots.entity.Team;


import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTeam is a Querydsl query type for Team
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTeam extends EntityPathBase<Team> {

    private static final long serialVersionUID = 507500894L;

    public static final QTeam team = new QTeam("team");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Short> league = createNumber("league", Short.class);

    public final StringPath shortTitle = createString("shortTitle");

    public final StringPath title = createString("title");

    public QTeam(String variable) {
        super(Team.class, forVariable(variable));
    }

    public QTeam(Path<? extends Team> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeam(PathMetadata<?> metadata) {
        super(Team.class, metadata);
    }

}

