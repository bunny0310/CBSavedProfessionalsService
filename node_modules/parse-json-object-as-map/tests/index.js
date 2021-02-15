var proclaim = require('proclaim');
var parseJSON = require('..');

suite('convert to map with an object', function() {
    test('a simple json', function() {
        var json = '{ "test": true }';

        var map = parseJSON(json);

        proclaim.instanceOf(map, Map);
        proclaim.strictEqual(map.size, 1);
        proclaim.isTrue(map.has('test'));
        proclaim.strictEqual(map.get('test'), true);
    });

    test('a simple json with an array', function() {
        var json = '[0,1,2,"test"]';

        var array = parseJSON(json);

        proclaim.instanceOf(array, Array);
        proclaim.strictEqual(array.length, 4);
        proclaim.strictEqual(array[0], 0);
        proclaim.strictEqual(array[1], 1);
        proclaim.strictEqual(array[2], 2);
        proclaim.strictEqual(array[3], 'test');
    });
});
