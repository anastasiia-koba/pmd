/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.java.ast;


/**
 * Represents a unary operation on a value. The syntactic form may be
 * prefix or postfix, which are represented with the same nodes, even
 * though they have different precedences.
 *
 * <pre class="grammar">
 *
 * UnaryExpression ::= PrefixExpression | PostfixExpression
 *
 * PrefixExpression  ::= {@link UnaryOp PrefixOp} UnaryExpression
 *
 * PostfixExpression ::= {@link ASTExpression Expression} ({@link UnaryOp PostfixOp})
 *
 * </pre>
 */
public class ASTUnaryExpression extends AbstractJavaExpr implements LeftRecursiveNode {

    private UnaryOp operator;

    ASTUnaryExpression(int id) {
        super(id);
    }

    ASTUnaryExpression(JavaParser p, int id) {
        super(p, id);
    }


    @Override
    public Object jjtAccept(JavaParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }


    @Override
    public <T> void jjtAccept(SideEffectingVisitor<T> visitor, T data) {
        visitor.visit(this, data);
    }


    /** Returns the expression nested within this expression. */
    public ASTExpression getOperand() {
        return (ASTExpression) jjtGetChild(0);
    }

    /** Returns the constant representing the operator. */
    public UnaryOp getOperator() {
        return operator;
    }

    /**
     * Returns true if this is a prefix expression.
     *
     * @deprecated XPath-attribute only, use {@code getOperator().isPrefix()} in java code.
     */
    @Deprecated
    public boolean isPrefix() {
        return getOperator().isPrefix();
    }

    void setOp(UnaryOp op) {
        this.operator = op;
    }

}
