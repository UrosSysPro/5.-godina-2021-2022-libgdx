uniform vec2 imgSize;

varying vec2 v_texCoords;

int preciznost=50;

float mandelbrotSet(vec2 pos){
    vec2 z=vec2(0);
    vec2 c=pos;
    float stop=preciznost;
    for(int i=0;i<preciznost;i++){
        vec2 p=vec2(0);
        p.x=z.x*z.x-z.y*z.y;
        p.y=2*z.x*z.y;
        p+=c;
        z.x=p.x;
        z.y=p.y;
        if(dot(p,p)>1&&stop==preciznost){
            stop=i;
        }
    }
    return stop/preciznost;
}

void main(){
    vec2 pos=v_texCoords;
//    pos.x/=screenSize.x;
//    pos.y/=screenSize.y;

    pos=pos*2-1;
    float boja= mandelbrotSet(pos);
//    vec4 a=vec4(1,1,1,1);
//    vec2 b=a.xz;

//    gl_FragColor=vec4(boja,boja,1,1);
    gl_FragColor=vec4(boja,boja,boja,1);
}