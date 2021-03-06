/*
 * Collect IDs plugin
 *
 * @param options - a map containing options. Options are sent from Java
 *
 * @return - an array of ids or uris
 */
function collect(options) {
  xdmp.log(['options', options]);
  return cts.uris(null, null, cts.collectionQuery(options.flow));
}

module.exports = {
  collect: collect
};
