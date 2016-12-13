/**
*非同期ダウンロードに使用するXMLHttpインスタンスを返します。 
*このメソッドは例外をスローしませんが、
*何らかの理由でブラウザがXmlHttpをサポートしていない場合はNULLを返します。
* @return {XMLHttpRequest | Null}
*/
function createXmlHttpRequest() {
 try {
   if (typeof ActiveXObject != 'undefined') {
     return new ActiveXObject('Microsoft.XMLHTTP');
   } else if (window["XMLHttpRequest"]) {
     return new XMLHttpRequest();
   }
 } catch (e) {
   changeStatus(e);
 }
 return null;
};

/**
*この関数は、XMLHttpRequestのopen / send関数をラップします。
* URLを指定して、ステータスコードが200の場合はコールバックを呼び出します。
* @param {String} url取得するURL
* @param {Function} callback一度呼び出す関数を取得します。
*/
function downloadUrl(url, callback) {
 var status = -1;
 var request = createXmlHttpRequest();
 if (!request) {
   return false;
 }

 request.onreadystatechange = function() {
   if (request.readyState == 4) {
     try {
       status = request.status;
     } catch (e) {
       // 通常、リクエストはFFでタイムアウトしたことを示します。
     }
     if ((status == 200) || (status == 0)) {
       callback(request.responseText, request.status);
       request.onreadystatechange = function() {};
     }
   }
 }
 request.open('GET', url, true);
 try {
   request.send(null);
 } catch (e) {
   changeStatus(e);
 }
};

/**
*指定されたXML文字列を解析し、解析されたドキュメントをDOMデータ構造体に返します。
*この関数は、XML解析がこのブラウザでサポートされていない場合、空のDOMノードを返します。
* @param {string} str XML文字列
* @return {Element | Document} DOM
*/
function xmlParse(str) {
  if (typeof ActiveXObject != 'undefined' && typeof GetObject != 'undefined') {
    var doc = new ActiveXObject('Microsoft.XMLDOM');
    doc.loadXML(str);
    return doc;
  }

  if (typeof DOMParser != 'undefined') {
    return (new DOMParser()).parseFromString(str, 'text/xml');
  }

  return createElement('div', null);
}

/**
*ページにJavaScriptファイルを追加します。
* @param {string} url
*/
function downloadScript(url) {
  var script = document.createElement('script');
  script.src = url;
  document.body.appendChild(script);
}