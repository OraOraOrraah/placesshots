package com.kugiojotaro.placesshots.entity.qdsl;

import static com.mysema.query.types.PathMetadataFactory.*;
import com.kugiojotaro.placesshots.entity.Fixture;


import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QFixture is a Querydsl query type for Fixture
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFixture extends EntityPathBase<Fixture> {

    private static final long serialVersionUID = 1270183784L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFixture fixture = new QFixture("fixture");

    protected QTeam away;

    public final NumberPath<Short> awayExtraTimeScore = createNumber("awayExtraTimeScore", Short.class);

    public final NumberPath<Short> awayPenaltyScore = createNumber("awayPenaltyScore", Short.class);

    public final NumberPath<Short> awayScore = createNumber("awayScore", Short.class);

    public final NumberPath<Integer> createBy = createNumber("createBy", Integer.class);

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> fixtureDate = createDateTime("fixtureDate", java.util.Date.class);

    protected QTeam home;

    public final NumberPath<Short> homeExtraTimeScore = createNumber("homeExtraTimeScore", Short.class);

    public final NumberPath<Short> homePenaltyScore = createNumber("homePenaltyScore", Short.class);

    public final NumberPath<Short> homeScore = createNumber("homeScore", Short.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Short> league = createNumber("league", Short.class);

    public final StringPath round = createString("round");

    public final NumberPath<Integer> updateBy = createNumber("updateBy", Integer.class);

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public final NumberPath<Short> week = createNumber("week", Short.class);

    public QFixture(String variable) {
        this(Fixture.class, forVariable(variable), INITS);
    }

    public QFixture(Path<? extends Fixture> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFixture(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFixture(PathMetadata<?> metadata, PathInits inits) {
        this(Fixture.class, metadata, inits);
    }

    public QFixture(Class<? extends Fixture> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.away = inits.isInitialized("away") ? new QTeam(forProperty("away")) : null;
        this.home = inits.isInitialized("home") ? new QTeam(forProperty("home")) : null;
    }

    public QTeam away() {
        if (away == null) {
            away = new QTeam(forProperty("away"));
        }
        return away;
    }

    public QTeam home() {
        if (home == null) {
            home = new QTeam(forProperty("home"));
        }
        return home;
    }

}

