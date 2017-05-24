package es.ucm.fdi.edd.ui.views.model;

import org.eclipse.swt.graphics.Rectangle;

import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangInfo;

public class SeqDiagramElementInfo<E extends ErlangInfo> {

	// Área en la que se encuentra localizado
	private Rectangle rectangle;

	private E erlangInfo;

	public SeqDiagramElementInfo(Rectangle rectangle, E erlangInfo) {
		this.rectangle = rectangle;
		this.erlangInfo = erlangInfo;
	}

	public boolean contains(int x, int y) {
		return this.rectangle.contains(x, y);
	}

	public E getErlangInfo() {
		return erlangInfo;
	}

	public void setErlangInfo(E erlangInfo) {
		this.erlangInfo = erlangInfo;
	}

	public int getY() {
		return this.rectangle.y;
	}
}
