package com.kugiojotaro.placesshots.entity.qdsl;

import static com.mysema.query.types.PathMetadataFactory.*;
import com.kugiojotaro.placesshots.entity.UserConnection;


import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUserConnection is a Querydsl query type for UserConnection
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserConnection extends EntityPathBase<UserConnection> {

    private static final long serialVersionUID = 1608175306L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserConnection userConnection = new QUserConnection("userConnection");

    public final StringPath accessToken = createString("accessToken");

    public final StringPath displayName = createString("displayName");

    public final NumberPath<Long> expireTime = createNumber("expireTime", Long.class);

    protected QUserConnectionId id;

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath profileUrl = createString("profileUrl");

    public final NumberPath<Integer> rank = createNumber("rank", Integer.class);

    public final StringPath refreshToken = createString("refreshToken");

    public final StringPath secret = createString("secret");

    public QUserConnection(String variable) {
        this(UserConnection.class, forVariable(variable), INITS);
    }

    public QUserConnection(Path<? extends UserConnection> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserConnection(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserConnection(PathMetadata<?> metadata, PathInits inits) {
        this(UserConnection.class, metadata, inits);
    }

    public QUserConnection(Class<? extends UserConnection> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QUserConnectionId(forProperty("id")) : null;
    }

    public QUserConnectionId id() {
        if (id == null) {
            id = new QUserConnectionId(forProperty("id"));
        }
        return id;
    }

}

