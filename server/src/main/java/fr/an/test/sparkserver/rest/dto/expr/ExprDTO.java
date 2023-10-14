package fr.an.test.sparkserver.rest.dto.expr;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.AllArgsConstructor;
import scala.annotation.meta.param;

@JsonSubTypes(value= {
    @JsonSubTypes.Type(value = ExprDTO.LiteralExprDTO.class),
    @JsonSubTypes.Type(value = ExprDTO.FieldAccessExprDTO.class),
    @JsonSubTypes.Type(value = ExprDTO.RefByIdLookupExprDTO.class),
    @JsonSubTypes.Type(value = ExprDTO.GroupAccumulatorExprDTO.class),
    @JsonSubTypes.Type(value = ExprDTO.BinaryOpExprDTO.class),
})
public abstract class ExprDTO {

    public abstract <TRes,TParam> TRes accept(SimpleExprDTOVisitor<TRes,TParam> visitor, TParam param);

    public static abstract class SimpleExprDTOVisitor<TRes,TParam> {
        public abstract TRes caseLiteral(ExprDTO.LiteralExprDTO expr, TParam param);
        public abstract TRes caseFieldAccess(ExprDTO.FieldAccessExprDTO expr, TParam param);
        public abstract TRes caseRefByIdLookup(ExprDTO.RefByIdLookupExprDTO expr, TParam param);
        public abstract TRes caseGroupAccumulator(ExprDTO.GroupAccumulatorExprDTO expr, TParam param);
        public abstract TRes caseBinaryOp(ExprDTO.BinaryOpExprDTO expr, TParam param);
    }


    @AllArgsConstructor
    public static class LiteralExprDTO extends ExprDTO {
        public Integer intValue;
        public Double doubleValue;
        public String strValue;

        @Override
        public <TRes,TParam> TRes accept(SimpleExprDTOVisitor<TRes,TParam> visitor, TParam param) {
            return visitor.caseLiteral(this, param);
        }

    }

    @AllArgsConstructor
    public static class FieldAccessExprDTO extends ExprDTO {
        public ExprDTO objExpr;
        public String field;

        @Override
        public <TRes, TParam> TRes accept(SimpleExprDTOVisitor<TRes, TParam> visitor, TParam param) {
            return visitor.caseFieldAccess(this, param);
        }
    }

    @AllArgsConstructor
    public static class RefByIdLookupExprDTO extends ExprDTO {
        public String namespace;
        public ExprDTO idExpr;

        @Override
        public <TRes,TParam> TRes accept(SimpleExprDTOVisitor<TRes,TParam> visitor, TParam param) {
            return visitor.caseRefByIdLookup(this, param);
        }

    }

    @AllArgsConstructor
    public static class GroupAccumulatorExprDTO extends ExprDTO {
        public String accumulatorFunction; // average, min, max, count, first, last, distinctSet, ...
        public ExprDTO expr;

        @Override
        public <TRes,TParam> TRes accept(SimpleExprDTOVisitor<TRes,TParam> visitor, TParam param) {
            return visitor.caseGroupAccumulator(this, param);
        }

    }


    @AllArgsConstructor
    public static class BinaryOpExprDTO extends ExprDTO {
        public ExprDTO left;
        // public EnumBinaryOp op;
        public String op;
        public ExprDTO right;

        @Override
        public <TRes,TParam> TRes accept(SimpleExprDTOVisitor<TRes,TParam> visitor, TParam param) {
            return visitor.caseBinaryOp(this, param);
        }

    }

}
