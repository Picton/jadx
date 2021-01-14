package jadx.core.dex.instructions;

import org.jetbrains.annotations.Nullable;

import jadx.api.plugins.input.data.MethodHandleType;
import jadx.api.plugins.input.insns.InsnData;
import jadx.core.dex.info.MethodInfo;
import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.utils.InsnUtils;

public class InvokeCustomNode extends InvokeNode {
	private MethodInfo implMthInfo;
	private MethodHandleType handleType;
	private InsnNode callInsn;
	private boolean inlineInsn;

	public InvokeCustomNode(MethodInfo lambdaInfo, InsnData insn, boolean instanceCall, boolean isRange) {
		super(lambdaInfo, insn, InvokeType.CUSTOM, instanceCall, isRange);
	}

	public MethodInfo getImplMthInfo() {
		return implMthInfo;
	}

	public void setImplMthInfo(MethodInfo implMthInfo) {
		this.implMthInfo = implMthInfo;
	}

	public MethodHandleType getHandleType() {
		return handleType;
	}

	public void setHandleType(MethodHandleType handleType) {
		this.handleType = handleType;
	}

	public InsnNode getCallInsn() {
		return callInsn;
	}

	public void setCallInsn(InsnNode callInsn) {
		this.callInsn = callInsn;
	}

	public boolean isInlineInsn() {
		return inlineInsn;
	}

	public void setInlineInsn(boolean inlineInsn) {
		this.inlineInsn = inlineInsn;
	}

	@Nullable
	public BaseInvokeNode getInvokeCall() {
		if (callInsn.getType() == InsnType.INVOKE) {
			return (BaseInvokeNode) callInsn;
		}
		return null;
	}

	@Override
	public @Nullable InsnArg getInstanceArg() {
		return null;
	}

	@Override
	public boolean isStaticCall() {
		return true;
	}

	@Override
	public int getFirstArgOffset() {
		return 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(InsnUtils.formatOffset(offset)).append(": INVOKE_CUSTOM ");
		if (getResult() != null) {
			sb.append(getResult()).append(" = ");
		}
		appendArgs(sb);
		sb.append("\n handle type: ").append(handleType);
		sb.append("\n lambda: ").append(implMthInfo);
		sb.append("\n call insn: ").append(callInsn);
		return sb.toString();
	}
}
