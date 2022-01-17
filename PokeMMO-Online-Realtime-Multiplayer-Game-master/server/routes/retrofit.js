/**
 * <pre>
 * Retrofit 관련 코드 수정
 * </pre>
 *
 * @author Soo Hyun Park
 * @since 2022. 01. 17
 */

var express = require('express');
var router = express.Router();

// get 호출 -> get success 리턴
router.get('/get', function (req, res, next) {
    console.log('GET 호출 / data : ' + req.query.data);
    console.log('path : ' + req.path);
    res.send('get success')
});

// getHello 호출 -> Hello ${req.query.data} 리턴
router.get('/getHello', function (req, res, next) {
    console.log('GET 호출 / data : ' + req.query.data);
    console.log('path : ' + req.path);
    res.send(`Hello ${req.query.data}`)
});

// post 호출 -> post success
router.post('/post', function (req, res, next) {
    console.log('POST 호출 / data : ' + req.body.data);
    console.log('path : ' + req.path);
    res.send('post success')
});

// put 호출(id 필요) -> put success
router.put('/put/:id', function (req, res, next) {
    console.log('UPDATE 호출 / id : ' + req.params.id);
    console.log('body : ' + req.body.data);
    console.log('path : ' + req.path);
    res.send('put success')
});

// delete 호출(id 필요) -> delete success
router.delete('/delete/:id', function (req, res, next) {
    console.log('DELETE 호출 / id : ' + req.params.id);
    console.log('path : ' + req.path);
    res.send('delete success')
});

module.exports = router;