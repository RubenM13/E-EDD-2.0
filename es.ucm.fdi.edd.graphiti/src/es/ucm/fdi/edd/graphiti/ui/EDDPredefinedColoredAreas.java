package es.ucm.fdi.edd.graphiti.ui;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.mm.algorithms.styles.AdaptedGradientColoredAreas;
import org.eclipse.graphiti.mm.algorithms.styles.GradientColoredArea;
import org.eclipse.graphiti.mm.algorithms.styles.GradientColoredAreas;
import org.eclipse.graphiti.mm.algorithms.styles.LocationType;
import org.eclipse.graphiti.mm.algorithms.styles.StylesFactory;
import org.eclipse.graphiti.util.IGradientType;
import org.eclipse.graphiti.util.IPredefinedRenderingStyle;
import org.eclipse.graphiti.util.PredefinedColoredAreas;

public class EDDPredefinedColoredAreas extends PredefinedColoredAreas {
	
	/** The ID for a red-to-white gradient. */
	public static final String RED_WHITE_ID = "red-white";

	/** The ID for a green-to-white gradient. */
	public static final String GREEN_WHITE_ID = "green-white";

	public static AdaptedGradientColoredAreas getAdaptedGradientColoredAreas(String id) {
		if (RED_WHITE_ID.equals(id)) {
			return getRedWhiteAdaptions();
		}
		if (GREEN_WHITE_ID.equals(id)) {
			return getGreenWhiteAdaptions();
		} else {
			return PredefinedColoredAreas.getAdaptedGradientColoredAreas(id);
		}
	}

	/**
	 * @return {@link AdaptedGradientColoredAreas} color-areas for
	 *         {@link #RED_WHITE_ID} with the adaptations:
	 *         {@link #STYLE_ADAPTATION_DEFAULT},
	 *         {@link #STYLE_ADAPTATION_PRIMARY_SELECTED},
	 *         {@link #STYLE_ADAPTATION_SECONDARY_SELECTED}.
	 */
	public static AdaptedGradientColoredAreas getRedWhiteAdaptions() {
		final AdaptedGradientColoredAreas agca = StylesFactory.eINSTANCE.createAdaptedGradientColoredAreas();
		agca.setDefinedStyleId(RED_WHITE_ID);
		agca.setGradientType(IGradientType.VERTICAL);
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_DEFAULT, getRedWhiteDefaultAreas());
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_PRIMARY_SELECTED, getRedWhitePrimarySelectedAreas());
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_SECONDARY_SELECTED, getRedWhiteSecondarySelectedAreas());
		return agca;
	}

	//
	// Red Stuff
	//
	/**
	 * The color-areas, which are used for default elements with the ID
	 * {@link #RED_WHITE_ID}.
	 */
	private static GradientColoredAreas getRedWhiteDefaultAreas() {
		final GradientColoredAreas gradientColoredAreas = StylesFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_DEFAULT);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();
		addGradientColoredArea(gcas, "F8D4E7", 0, LocationType.LOCATION_TYPE_ABSOLUTE_START, "FCFAFB", 0, LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	/**
	 * The color-areas, which are used for primary selected elements with the ID
	 * {@link #RED_WHITE_ID}.
	 */
	private static GradientColoredAreas getRedWhitePrimarySelectedAreas() {
		final GradientColoredAreas gradientColoredAreas = StylesFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_PRIMARY_SELECTED);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();
		addGradientColoredArea(gcas, "EA81B9", 0, LocationType.LOCATION_TYPE_ABSOLUTE_START, "F2AAD0", 0, LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	/**
	 * The color-areas, which are used for secondary selected elements with the
	 * ID {@link #RED_WHITE_ID}.
	 */
	private static GradientColoredAreas getRedWhiteSecondarySelectedAreas() {
		final GradientColoredAreas gradientColoredAreas = StylesFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_SECONDARY_SELECTED);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();
		addGradientColoredArea(gcas, "F7BBDA", 0, LocationType.LOCATION_TYPE_ABSOLUTE_START, "F7C5E0", 0, LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	//
	// Green Stuff
	//
	/**
	 * @return {@link AdaptedGradientColoredAreas} color-areas for
	 *         {@link #GREEN_WHITE_ID} with the adaptations:
	 *         {@link #STYLE_ADAPTATION_DEFAULT},
	 *         {@link #STYLE_ADAPTATION_PRIMARY_SELECTED},
	 *         {@link #STYLE_ADAPTATION_SECONDARY_SELECTED}.
	 */
	public static AdaptedGradientColoredAreas getGreenWhiteAdaptions() {
		final AdaptedGradientColoredAreas agca = StylesFactory.eINSTANCE.createAdaptedGradientColoredAreas();
		agca.setDefinedStyleId(GREEN_WHITE_ID);
		agca.setGradientType(IGradientType.VERTICAL);
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_DEFAULT, getGreenWhiteDefaultAreas());
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_PRIMARY_SELECTED, getGreenWhitePrimarySelectedAreas());
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_SECONDARY_SELECTED, getGreenWhiteSecondarySelectedAreas());
		return agca;
	}

	/**
	 * The color-areas, which are used for default elements with the ID
	 * {@link #GREEN_WHITE_ID}.
	 */
	private static GradientColoredAreas getGreenWhiteDefaultAreas() {
		final GradientColoredAreas gradientColoredAreas = StylesFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_DEFAULT);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();
		addGradientColoredArea(gcas, "E7F8D4", 0, LocationType.LOCATION_TYPE_ABSOLUTE_START, "FBFCFA", 0, LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	/**
	 * The color-areas, which are used for primary selected elements with the ID
	 * {@link #GREEN_WHITE_ID}.
	 */
	private static GradientColoredAreas getGreenWhitePrimarySelectedAreas() {
		final GradientColoredAreas gradientColoredAreas = StylesFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_PRIMARY_SELECTED);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();
		addGradientColoredArea(gcas, "B9EA81", 0, LocationType.LOCATION_TYPE_ABSOLUTE_START, "D0F2AA", 0, LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	/**
	 * The color-areas, which are used for secondary selected elements with the
	 * ID {@link #GREEN_WHITE_ID}.
	 */
	private static GradientColoredAreas getGreenWhiteSecondarySelectedAreas() {
		final GradientColoredAreas gradientColoredAreas = StylesFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_SECONDARY_SELECTED);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();
		addGradientColoredArea(gcas, "DAF7BB", 0, LocationType.LOCATION_TYPE_ABSOLUTE_START, "E0F7C5", 0, LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}
}