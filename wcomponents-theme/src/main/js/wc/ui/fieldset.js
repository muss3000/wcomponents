define(["wc/dom/initialise",
	"wc/ui/ajax/processResponse",
	"wc/ui/getFirstLabelForElement",
	"wc/dom/Widget",
	"wc/dom/tag",
	"wc/ui/onchangeSubmit"],
	function(initialise, processResponse, getFirstLabelForElement, Widget, tag, onchangeSubmit) {
		"use strict";
		/**
		 * @constructor
		 * @alias module:wc/ui/fieldset~Fieldset
		 * @private
		 */
		function Fieldset() {
			var FSET = new Widget("fieldset"),
				WRAPPER = FSET.extend("wc-fset-wrapper");

			function makeLegend(el) {
				var label = el.firstChild,
					accesskey,
					labelContent;

				// quickly jump out if we have already got a legend in this fieldset.
				while (label && label.nodeType !== Node.ELEMENT_NODE) {
					label = label.nextSibling;
				}
				if (label && label.tagName === tag.LEGEND) {
					return;
				}
				label = getFirstLabelForElement(el);
				if (!label) {
					labelContent = el.getAttribute("aria-label");
					if (labelContent) {
						el.removeAttribute("aria-label");
					} else {
						labelContent = el.getAttribute("title");
						el.removeAttribute("title");
					}
				} else {
					labelContent = label.innerHTML;
					accesskey = label.getAttribute("data-wc-accesskey");
				}
				el.insertAdjacentHTML("afterbegin", "<legend class='wc-moved-label'" + (accesskey ? " accesskey = '" + accesskey + "'" : "") + ">" + labelContent + "</legend>");
				onchangeSubmit.warn(el, el.firstChild);
			}

			function labelToLegend(element) {
				var el = element || document.body;
				if (element && WRAPPER.isOneOfMe(el)) {
					makeLegend(el);
				} else {
					Array.prototype.forEach.call(WRAPPER.findDescendants(el), makeLegend);
				}
			}

			/**
			 * Gets the {@link module:wc/dom/Widget} descriptor for a fieldset element.
			 *
			 * @function module:wc/ui/fieldset.getWidget
			 * @public
			 * @param {boolean} [requireWrapper] if truthy get the WRAPPER extension of the Widget.
			 * @returns {module:wc/dom/Widget} The description of a fieldset.
			 */
			this.getWidget = function(requireWrapper) {
				return requireWrapper ? WRAPPER : FSET;
			};

			/**
			 * Initialiser callback. For internal use only.
			 *
			 * @function module:wc/ui/fieldset.preInit
			 * @public
			 */
			this.preInit = function() {
				labelToLegend();
				processResponse.subscribe(labelToLegend, true);
			};
		}

		/**
		 * Provides functionality peculiar to FIELDSET elements around ensuring that the contained controls and legends
		 * are correctly synchronised after AJAX transactions.
		 *
		 * @module
		 * @requires module:wc/dom/initialise
		 * @requires module:wc/ui/ajax/processResponse
		 * @requires module:wc/ui/getFirstLabelForElement
		 * @requires module:wc/dom/Widget
		 * @requires module:wc/dom/tag
		 */
		var instance = new Fieldset();
		initialise.register(instance);
		return instance;
	});
