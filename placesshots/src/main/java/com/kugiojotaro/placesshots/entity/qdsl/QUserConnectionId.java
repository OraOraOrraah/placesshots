package com.kugiojotaro.placesshots.entity.qdsl;

import static com.mysema.query.types.PathMetadataFactory.*;
import com.kugiojotaro.placesshots.entity.UserConnectionId;


import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QUserConnectionId is a Querydsl query type for UserConnectionId
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class QUserConnectionId extends BeanPath<UserConnectionId> {

    private static final long serialVersionUID = -731755131L;

    public static final QUserConnectionId userConnectionId = new QUserConnectionId("userConnectionId");

    public final StringPath providerId = createString("providerId");

    public final StringPath providerUserId = createString("providerUserId");

    public final StringPath userId = createString("userId");

    public QUserConnectionId(String variable) {
        super(UserConnectionId.class, forVariable(variable));
    }

    public QUserConnectionId(Path<? extends UserConnectionId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserConnectionId(PathMetadata<?> metadata) {
        super(UserConnectionId.class, metadata);
    }

}

