uniform vec2 start;
uniform vec2 end;
uniform float iterations;
uniform sampler2D u_texture;

varying vec2 v_texCoords;

vec2 setPos(vec2 pos){
    pos.x*=(end.x-start.x);
    pos.y*=(end.y-start.y);
    pos+=start;
    return pos;
}

vec4 mandelbrotSet(vec2 pos){
    vec2 z = vec2(0);
    vec2 c = pos;
    float n=float(0);
    for(float  i = float(0); i < iterations; i++){
        vec2 p = vec2(0);
        p.x = (z.x * z.x) - (z.y * z.y);
        p.y = float(2) * z.x * z.y;
////        vec2 p=vec2(z.x * z.x - z.y * z.y,2 * z.x * z.y);
        p+=c;
        z=p;
        if(dot(p,p)<=float(2)){
            n++;
        }
    }
    n/=iterations;
    return vec4(n,n,n,1);
}

void main(){
    vec2 pos=setPos(v_texCoords);
    vec4 color=mandelbrotSet(pos);
//    gl_FragColor=vec4(pos.x,pos.y,0,1);
    gl_FragColor=color;
}